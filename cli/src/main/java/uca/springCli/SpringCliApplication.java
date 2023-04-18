package uca.springCli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.standard.commands.Clear;


import java.io.IOException;

@SpringBootApplication
public class SpringCliApplication {


	public static void main(String[] args) throws IOException, InterruptedException {
	runAnimation();
	SpringApplication.run(SpringCliApplication.class, args);
	}
	String red = "\033[31m";


	public static void runAnimation() throws InterruptedException, IOException {



		String art = "   -           __\n" +
				" --          ~( @\\   \\\n" +
				"---   " + "\033[31m" + "_________"+ "\033[0m" +"]_[ " + "\033[31m" + "__/_>________\n" + "\033[0m"  +
				"\033[31m" + "     /  ____ \\ <>     |  ____  \\\n"+ "\033[0m" +
				"\033[31m" + "    =\\_/ "+ "\033[0m" +"__" + "\033[31m" + " \\_\\_______|_/ " + "\033[0m" +"__" + "\033[31m" + " \\__D\n"+ "\033[0m" +
				"________(__)_____________(__)____\n";


		int maxOffset = 44;
		int offset = 0;
		Clear a = new Clear();


		while (true) {
			String[] artLines = art.split("\n");
			for (String line : artLines) {
				//if its the last line
				if (line.equals(artLines[artLines.length - 1])) {
					//System.out.println("last line");
					for (int i = 0; i < offset; i++) {
						System.out.print("\033[0m" + "__");

					}
					System.out.println(line);
					break;
				}
				else
				for (int i = 0; i < offset; i++) {
					System.out.print("  ");

				}
				System.out.println(line);

			}



			offset++;
			if (offset > maxOffset) {
				break;
			}

			Thread.sleep(30);
			// borra la consola
			//System.out.flush();

			System.out.print("\033[H\033[2J");
		}
	}

}
