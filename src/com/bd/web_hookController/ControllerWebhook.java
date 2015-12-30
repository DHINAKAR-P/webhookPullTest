package com.bd.web_hookController;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geppetto")
public class ControllerWebhook {
	
	@RequestMapping(value = "/webhook", method = RequestMethod.POST ,headers = "Accept=application/json", produces = "text/html")
	public Object first(@RequestBody Object ob) {
		String commandline="";
		try {
			System.out.println("----test ---");
			String task = " warGeneration";
			Process p = Runtime.getRuntime().exec("gradle -q  " + task, null, new File("/root/master_Geppetto/"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				commandline+=("\n"+line);
			}
			p.waitFor();
			p.destroy();
		} catch (IOException e1) {
		} catch (InterruptedException e2) {
		}
		return commandline;
	}
 
}