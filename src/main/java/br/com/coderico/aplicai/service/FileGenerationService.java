package br.com.coderico.aplicai.service;

import br.com.coderico.aplicai.entity.resume.Resume;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class FileGenerationService {

    private final SpringTemplateEngine templateEngine;

    public byte[] generatePdf(Resume resume) {
        String resumeHtml = getHtml(resume);

        try (var outputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(resumeHtml, null)
                    .toStream(outputStream)
                    .run();

            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Falha ao gerar arquivo para o currículo");
        }
    }

    private String getHtml(Resume resume) {
        var context = new Context();
        context.setVariable("resume", resume);

        return templateEngine.process("resumes/default.html", context);
    }
}
