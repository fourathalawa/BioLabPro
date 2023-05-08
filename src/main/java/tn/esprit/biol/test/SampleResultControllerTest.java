/*package tn.esprit.biol.test;

import com.google.zxing.WriterException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tn.esprit.biol.controller.SampleResultController;
import tn.esprit.biol.dao.SampleResultRepository;
import tn.esprit.biol.entity.SampleResult;
import tn.esprit.biol.entity.User;
import tn.esprit.biol.service.ISampleResultService;

import javax.print.attribute.standard.DateTimeAtCreation;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(SampleResultController.class)
public class SampleResultControllerTest {

    @Mock
    private ISampleResultService sampleResultService;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private SampleResultController sampleResultController;

    @Mock
    private SampleResultRepository sampleResultRepository;

    @Mock
    private SampleResult sampleResult;

    @Mock
    private List<SampleResult> sampleResults;

    @Mock
    private User user;

    @Mock
    private Message message;

    @Mock
    private Twilio twilio;

    @Mock
    private OutputStream outputStream;

    @Mock
    private PdfWriter pdfWriter;

    @Mock
    private Document document;

    @Mock
    private Paragraph paragraph;

    @Mock
    private PdfPTable table;

    private final int RESULT_ID = 1;
    private final int SAMPLE_ID = 2;
    private final String USER_TEL = "+1234567890";
    private final String QR_CODE_FILE_PATH = "C:/pitest/" + RESULT_ID + ".png";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        // given
        when(sampleResultService.getAll()).thenReturn(sampleResults);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/sampleResult/getAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    void getByID() throws Exception {
        // given
        when(sampleResultService.getById(RESULT_ID)).thenReturn(sampleResult);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/sampleResult/getByID/{resultID}", RESULT_ID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{ \"resultID\": 1 }"));
    }

    @Test
    void getBySampleID() throws Exception {
        // given
        when(sampleResultService.getById(SAMPLE_ID)).thenReturn(sampleResult);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/sampleResult/getBySampleID/{sampleID}", SAMPLE_ID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    void generateQRCode() throws IOException, WriterException {
        // given
        when(sampleResultService.getById(RESULT_ID)).thenReturn(sampleResult);
         when(user.getTel()).thenReturn(USER_TEL);

        // when
        sampleResultController.generateQRCodeForSampleResult(RESULT_ID);

        // then
        // TODO: Verify QR code is generated and saved at the correct file path
    }

    @Test
    void sendResultBySMS() throws Exception {
        // given
        when(sampleResultService.getById(RESULT_ID)).thenReturn(sampleResult);
         when(user.getTel()).thenReturn(USER_TEL);
         when(message.getSid()).thenReturn("SMS_ID");

        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/sampleResult/sendResultBySMS/{resultID}", RESULT_ID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("SMS sent successfully!"));

        // then
        // TODO: Verify SMS is sent to the correct phone number
    }


}*/