package tn.esprit.biol.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class PredictionService  {
    private static final List<Integer> x = asList(45, 35, 48, 35, 54, 20); // Nombre de rendez vous passé par jours
    private static final List<Integer> y = asList(44, 30, 50, 27, 49, 21); // Nombre de prelevement faite dans le meme jours
    private double result =0 ;


    private static Double predictForValue(int predictForDependetnVariable) {



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

    public static void main(String[] args) {
         double tubes =  predictForValue(50) +10 ;
        System.out.println("le nombre de prelevement estimé pour demain" + predictForValue(50) +
                "\n le nombre de tube d'essai estimé pour demain " + tubes+
                "\n ");
    }



}
