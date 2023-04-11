package uca.dss.SpringCli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.shell.standard.commands.Clear;

import java.io.IOException;

@SpringBootApplication
public class SpringCliApplication {




	public static void main(String[] args) throws IOException, InterruptedException {
	runAnimation();
	SpringApplication.run(SpringCliApplication.class, args);
	}

	public static void runAnimation() throws InterruptedException, IOException {
		String art = "   -           __\n" +
				" --          ~( @\\   \\\n" +
				"---   " + "\033[31m" + "_________"+ "\033[0m" +"]_[ " + "\033[31m" + "__/_>________\n" + "\033[0m"  +
				"\033[31m" + "     /  ____ \\ <>     |  ____  \\\n"+ "\033[0m" +
				"\033[31m" + "    =\\_/ "+ "\033[0m" +"__" + "\033[31m" + " \\_\\_______|_/ " + "\033[0m" +"__" + "\033[31m" + " \\__D\n"+ "\033[0m" +
				"________(__)_____________(__)____\n";


		String logo = " .----------------.  .----------------.  .----------------.  .-----------------. .----------------.  .----------------.  \n"
				+ "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. | \n"
				+ "| |  ___  ____   | || |  _______     | || |  _________   | || | ____  _____  | || |  ___  ____   | || |     ____     | | \n"
				+ "| | |_  ||_  _|  | || | |_   __ \\    | || | |_   ___  |  | || ||_   \\|_   _| | || | |_  ||_  _|  | || |   .'    `.   | | \n"
				+ "| |   | |_/ /    | || |   | |__) |   | || |   | |_  \\_|  | || |  |   \\ | |   | || |   | |_/ /    | || |  /  .--.  \\  | | \n"
				+ "| |   |  __'.    | || |   |  __ /    | || |   |  _|  _   | || |  | |\\ \\| |   | || |   |  __'.    | || |  | |    | |  | | \n"
				+ "| |  _| |  \\ \\_  | || |  _| |  \\ \\_  | || |  _| |___/ |  | || | _| |_\\   |_  | || |  _| |  \\ \\_  | || |  \\  `--'  /  | | \n"
				+ "| | |____||____| | || | |____| |___| | || | |_________|  | || ||_____|\\____| | || | |____||____| | || |   `.____.'   | | \n"
				+ "| |              | || |              | || |              | || |              | || |              | || |              | | \n"
				+ "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' | \n"
				+ ".----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------. \n" +
				"| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. | \n" +
				"| | ____   ____  | || |  _________   | || |  ____  ____  | || |     _____    | || |     ______   | || | _____  _____ | || |   _____      | || |     ____     | || |    _______   | | \n" +
				"| ||_  _| |_  _| | || | |_   ___  |  | || | |_   ||   _| | || |    |_   _|   | || |   .' ___  |  | || ||_   _||_   _|| || |  |_   _|     | || |   .'    `.   | || |   /  ___  |  | | \n" +
				"| |  \\ \\   / /   | || |   | |_  \\_|  | || |   | |__| |   | || |      | |     | || |  / .'   \\_|  | || |  | |    | |  | || |    | |       | || |  /  .--.  \\  | || |  |  (__ \\_|  | | \n" +
				"| |   \\ \\ / /    | || |   |  _|  _   | || |   |  __  |   | || |      | |     | || |  | |         | || |  | '    ' |  | || |    | |   _   | || |  | |    | |  | || |   '.___`-.   | | \n" +
				"| |    \\ ' /     | || |  _| |___/ |  | || |  _| |  | |_  | || |     _| |_    | || |  \\ `.___.'\\  | || |   \\ `--' /   | || |   _| |__/ |  | || |  \\  `--'  /  | || |  |`\\____) |  | | \n" +
				"| |     \\_/      | || | |_________|  | || | |____||____| | || |    |_____|   | || |   `._____.'  | || |    `.__.'    | || |  |________|  | || |   `.____.'   | || |  |_______.'  | | \n" +
				"| |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | | \n" +
				"| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' | \n" +
				" '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' ";


		int maxOffset = 25;
		int offset = 0;
		Clear a = new Clear();


		while (true) {
			String[] artLines = art.split("\n");
			for (String line : artLines) {
				for (int i = 0; i < offset; i++) {
					System.out.print(" ");
				}
				System.out.println(line);

			}
			System.out.println();
			System.out.println(logo);


			offset++;
			if (offset > maxOffset) {
				break;
			}

			Thread.sleep(100);
			// borra la consola
			//System.out.flush();

			System.out.print("\033[H\033[2J");
		}
	}

}