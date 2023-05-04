package tn.esprit.biol.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import tn.esprit.biol.dao.AppointementDao;
import tn.esprit.biol.entity.Appointement;


@Service

public class PredictionService  {
    @Autowired
    AppointementDao appointementDao;


//    private static final List<Integer> x = asList(45, 35, 48, 35, 54, 20); // Nombre de rendez vous passé par jours
//    private static final List<Integer> y = asList(44, 30, 50, 27, 49, 21); // Nombre de prelevement faite dans le meme jours


    public  Double predictForValue(int predictForDependetnVariable) {


        List<Appointement> appointements =  appointementDao.findAll();



            //nombre de rendezvous passé
            Map<LocalDate, List<Appointement>> appointmentsByDay1 = appointements.stream()
                    .filter(a->a.getStatusAppointement()==0)
                    .collect(Collectors.groupingBy(a -> a.getDateAppointement().toLocalDate()))
                    ;
            System.out.println(appointmentsByDay1 + "hhhhhhhhhhhhhhhhhh");

            List<Integer> xprim = new ArrayList<>();

// Iterate over each entry in the HashMap and add the size of its associated list to the sizesList
            for (Map.Entry<LocalDate, List<Appointement>> entry : appointmentsByDay1.entrySet()) {
                xprim.add(entry.getValue().size());
            }
            final List<Integer> x = xprim.stream().limit(5).collect(Collectors.toList());
            System.out.println(x+"aaaaaaaaaaaaaa");



            //nombre de test faite lem meme jours
            Map<LocalDate, List<Appointement>> appointmentsByDay = appointements.stream()
                    .filter(a->a.getStatusAppointement()==1)
                    .collect(Collectors.groupingBy(a -> a.getDateAppointement().toLocalDate()))
                    ;
            System.out.println(appointmentsByDay + "hhhhhhhhhhhhhhhhhh");

            List<Integer> yprim = new ArrayList<>();

// Iterate over each entry in the HashMap and add the size of its associated list to the sizesList
            for (Map.Entry<LocalDate, List<Appointement>> entry : appointmentsByDay.entrySet()) {
                yprim.add(entry.getValue().size());
            }
             final  List<Integer> y = yprim.stream().limit(5).collect(Collectors.toList());
            System.out.println(y+"aaaaaaaaaaaaaa");






        // verification de la conformité des données
        if (x.size() != y.size())
            throw new IllegalStateException("Must have equal X and Y data points");

        Integer numberOfDataValues = x.size();

        // recalcul de x au carré
        List<Double> xSquared = x
                .stream()
                .map(position -> Math.pow(position, 2))
                .collect(Collectors.toList());

        // multuplier x * y
        List<Integer> xMultipliedByY = IntStream.range(0, numberOfDataValues)
                .map(i -> x.get(i) * y.get(i))
                .boxed()
                .collect(Collectors.toList());
           // somme de x
        Integer xSummed = x
                .stream()
                .reduce((prev, next) -> prev + next)
                .get();
         // somme de y
        Integer ySummed = y
                .stream()
                .reduce((prev, next) -> prev + next)
                .get();
           // somme x au carré
        Double sumOfXSquared = xSquared
                .stream()
                .reduce((prev, next) -> prev + next)
                .get();
      // somme de x*y
        Integer sumOfXMultipliedByY = xMultipliedByY
                .stream()
                .reduce((prev, next) -> prev + next)
                .get();
  // calcul de pente : slopeNominator/slopeDeNominatior
        //etape 1 calcul slopeNominatior
        int slopeNominator = numberOfDataValues * sumOfXMultipliedByY - ySummed * xSummed;
        //etape 2 calcul slopeDenominatior
        Double slopeDenominator = numberOfDataValues * sumOfXSquared - Math.pow(xSummed, 2);
        // calc pente
        Double slope = slopeNominator / slopeDenominator;

        // calcul d'interception interceptNominator / interceptDenominator
        double interceptNominator = ySummed - slope * xSummed;

        double interceptDenominator = numberOfDataValues;

        Double intercept = interceptNominator / interceptDenominator;

        return  (slope * predictForDependetnVariable) + intercept;

    }




}
