package com.ericsson.v1.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		ApplicationContext context = new FileSystemXmlApplicationContext
				("///home/ekhiqba//EricssonApps//IPMWorkbook//conf//test-spring-dao-conf.xml"); 
		System.out.println("context "+context);
	}

}
