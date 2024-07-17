package com.fulinlin;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import com.fulinlin.entity.Hello;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportWordTest {


    public static final String OUTPUT_FILE_PATH = "C:\\Users\\FuLin\\Desktop\\out_template.docx";


    @Test
    public void testPoiTl() throws Exception {

        ClassPathResource classPathResource = new ClassPathResource("template/poi-tl.docx");
        InputStream in = classPathResource.getInputStream();

        Map<String, Object> data = getStringObjectMap();
        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();
        Configure config = Configure.builder()
                .bind("remarks", policy)
                .useSpringEL()
                .build();
        XWPFTemplate.compile(in, config)
                .render(data)
                .writeToFile(OUTPUT_FILE_PATH);
    }

    @Test
    public void testXDocReport() {
        try {
            // 1) Load Docx file by filling Velocity template engine and cache it to the registry
            ClassPathResource classPathResource = new ClassPathResource("template/xdocreport.docx");
            InputStream in = classPathResource.getInputStream();
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

            // 2) Create context Java model
            IContext context = report.createContext();
            Map<String, Object> data = getStringObjectMap();
            context.putMap(data);
            // 3) Generate report by merging Java model with the Docx
            OutputStream out = Files.newOutputStream
                    (new File(OUTPUT_FILE_PATH).toPath());
            report.process(context, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XDocReportException e) {
            e.printStackTrace();
        }
    }


    private static Map<String, Object> getStringObjectMap() {
        Map<String, Object> datas = new HashMap<>();
        List<Hello> hellos = new ArrayList<>();
        List<Hello.Remark> remarks1 = new ArrayList<>();
        remarks1.add(new Hello.Remark("备注1", "备注1"));
        remarks1.add(new Hello.Remark("备注1", "备注1"));
        remarks1.add(new Hello.Remark("备注1", "备注1"));
        remarks1.add(new Hello.Remark("备注1", "备注1"));
        remarks1.add(new Hello.Remark("备注1", "备注1"));
        remarks1.add(new Hello.Remark("备注1", "备注1"));
        hellos.add(new Hello("1", "张三1", remarks1));

        List<Hello.Remark> remarks2 = new ArrayList<>();
        remarks2.add(new Hello.Remark("备注2", "备注2"));
        remarks2.add(new Hello.Remark("备注2", "备注2"));
        remarks2.add(new Hello.Remark("备注2", "备注2"));
        remarks2.add(new Hello.Remark("备注2", "备注2"));
        remarks2.add(new Hello.Remark("备注2", "备注2"));
        remarks2.add(new Hello.Remark("备注2", "备注2"));
        hellos.add(new Hello("2", "张三2", remarks2));

        List<Hello.Remark> remarks3 = new ArrayList<>();
        remarks3.add(new Hello.Remark("备注3", "备注3"));
        remarks3.add(new Hello.Remark("备注3", "备注3"));
        remarks3.add(new Hello.Remark("备注3", "备注3"));
        remarks3.add(new Hello.Remark("备注3", "备注3"));
        remarks3.add(new Hello.Remark("备注3", "备注3"));
        remarks3.add(new Hello.Remark("备注3", "备注3"));
        hellos.add(new Hello("3", "张三3", remarks3));

        datas.put("hello", hellos);
        return datas;
    }


}
