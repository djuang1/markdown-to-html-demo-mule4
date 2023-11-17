package com.dejim;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;

import java.io.IOException;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class MarkdownUtility {

	public MarkdownUtility() {

	}
	
	public static String convertMdToHtml(InputStream body) throws IOException {

		MutableDataSet options = new MutableDataSet();
		Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        String html = "";
        
        try {
            String result = convertInputStreamToString(body);
            
            Node document = parser.parse(result);                        
            html = renderer.render(document);
            
    		return html;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		return html;
	}
	
	public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString(StandardCharsets.UTF_8.name());
    }

	public static void main(String[] args) throws IOException {		
		FileInputStream fi = new FileInputStream("/Users/djuang/Workspaces/seven-sixteen/markdown-to-html-demo-mule4/src/main/resources/test.md");
		System.out.print(convertMdToHtml(fi));
	}
	
}
