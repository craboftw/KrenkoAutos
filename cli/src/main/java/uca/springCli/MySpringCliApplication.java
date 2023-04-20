package uca.springCli;

import org.springframework.boot.ResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.shell.standard.commands.Clear;
import org.springframework.boot.Banner;



import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Vector;

@SpringBootApplication
public class MySpringCliApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		runAnimation();
		SpringApplication app = new SpringApplication(MySpringCliApplication.class);
		//app.setAdditionalProfiles("cli");
		//spring.banner.image.location=banner.gif
		//spring.banner.image.width=80
		//spring.banner.image.height=40
		//spring.banner.image.pixelmode=BLOCK
		//spring.banner.image.margin=4
		//spring.banner.image.bitdepth=7
		Map<String, Object> map = Map.of(
				"spring.banner.image.location", "banner.gif",
				"spring.banner.image.width", 80,
				"spring.banner.image.height", 40,
				"spring.banner.image.pixelmode", "BLOCK",
				"spring.banner.image.margin", 4,
				"spring.banner.image.bitdepth", 7
		);
		app.setDefaultProperties(map);
		app.run(args);
	}

	// ...





	/*
	*
		public static void main(String[] args) throws IOException, InterruptedException {
		runAnimation();
		SpringApplication app = new SpringApplication(MySpringCliApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.setLogStartupInfo(false);
		app.run(args);
	}
	*
	* */


	public static void runAnimation() throws InterruptedException, IOException {

		String logo;
		String red = "\033[31m";
		String reset = "\033[0m";
		String art = "   -           __\n" +
				" --          ~( @\\   \\\n" +
				"---   " + "\033[31m" + "_________"+ "\033[0m" +"]_[ " + "\033[31m" + "__/_>________\n" + "\033[0m"  +
				"\033[31m" + "     /  ____ \\ <>     |  ____  \\\n"+ "\033[0m" +
				"\033[31m" + "    =\\_/ "+ "\033[0m" +"__" + "\033[31m" + " \\_\\_______|_/ " + "\033[0m" +"__" + "\033[31m" + " \\__D\n"+ "\033[0m" +
				"________(__)_____________(__)____\n";


		int maxOffset = 42;
		int offset = 0;
//crear vector de 44 posiciones
		Vector<String> vectorColores = new Vector<String>(44);

		//asigno a cada posicion del vector el color reset
		for (int i = 0; i < 44; i++) {
			vectorColores.add(reset);
		}

		Vector<String> vector = new Vector<String>(44);
		for (int i = 0; i < offset; i++) {
			vector.set(i,reset);
		}


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
			//vector de 44 posiciones
			//el vector siempre tendra la posicion offset en rojo y la anterior de haberla en blanco



			if (offset == 0) {
				vectorColores.set(0,red);
				vectorColores.set(1,reset);
				vectorColores.set(2,reset);

			}
			else if (offset<maxOffset)
			{
				vectorColores.set(offset+2,red);
				vectorColores.set(offset-1,reset);
			}
			offset++;



			logo=
					"█████████████████████████████████████████████████████████████████████████████████████████████████████████████████████\n" + vectorColores.get(0) +
					"████████████    █████   ███████           ████████         ████████           ████████    ████    ███████         ███\n" + vectorColores.get(1) +
					"███████████      ███     █████             █████ *          ██████             ██████     ████    █████ *           █\n" + vectorColores.get(2) +
					"███████████*    ████*  *█████      ██      ████    *██      ██████*    ██*     █████      ███     ████    *██       █\n" + vectorColores.get(3) +
					"██████████     ████     █████     ████   *████      ███     █████     ███     ██████     ████   *█████*    ███     ██\n" + vectorColores.get(4) +
					"██████████     ████   *█████     ████     ████*    ████   *█████      ███    ██████     ████     ████     ███      ██\n" + vectorColores.get(5) +
					"█████████*   *████     ████      ████   *████     ██████████████*    ███     █████      ███    *████    *████*    ███\n" + vectorColores.get(6) +
					"████████      ███     █████*   *████*   █████     █████████████    *███     ██████    *███ *  *█████     ███     ████\n" + vectorColores.get(7) +
					"████████*   *██ *  * █████      ██     █████    *██████████████     ███*    █████      █      █████     ████     ████\n" + vectorColores.get(8) +
					"███████        *  ████████      *    ██████     ██████████████     ███     ██████*     * *  ██████     ████     █████\n" + vectorColores.get(9) +
					"██████          █████████          ████████*       *█████████     ████*    █████          ████████*    ███     ██████\n" + vectorColores.get(10) +
					"██████*          *██████     **      █████     **** █████████     ███     █████     **      █████     ████     ██████\n" + vectorColores.get(11) +
					"█████      ██     ██████     ███     █████     █████████████     ███     ██████*   *███     ████      ███    *███████\n" + vectorColores.get(12) +
					"█████     ███     █████      ███     ████     ██████████████     ███     █████     ████     ████     ████     ███████\n" + vectorColores.get(13) +
					"████    *████     █████     ███     ████    *██████████████*    ███     ██████     ███    *████    *████     ████████\n" + vectorColores.get(14) +
					"███      ███     █████    *███     █████*    ███     █████    *████     █████     ███     █████     ███     █████████\n" + vectorColores.get(15) +
					"███*    ███     █████      ███     ████     ███     ██████*    ███     █████      ███     ████      ███     █████████\n" + vectorColores.get(16) +
					"██    *████*    █████*    ████   *████      ███*    █████     ███     ██████*    ████   *████       ██     ██████████\n" + vectorColores.get(17) +
					"█      ███     █████    *████     ████             ██████    ████     █████    *████*    ████             ███████████\n" + vectorColores.get(18) +
					"█  ** ████ *   █████ **  ████    █████ ** ******* ██████   * ████*** ██████ **  ████ ** ██████  ***** *  ████████████\n" + vectorColores.get(19) +
					"█████████████████████████████████████████████████████████████████████████████████████████████████████████████████████\n" + vectorColores.get(20) +
					"████████████           ██████ ** ████     ███ *   ******* █████   * ****  ████████     ***** ████████████████████████\n" + vectorColores.get(21) +
					"███████████            █████     ████    ████        *    ████    *        ██████            ████████████████████████\n" + vectorColores.get(22) +
					"██████████     ███    *█████   *████     ███████    *████████     ███      █████     ███*    ████████████████████████\n" + vectorColores.get(23) +
					"██████████     ███     ████     ███     ███████      ████████*    ███    *█████     ████    █████████████████████████\n" + vectorColores.get(24) +
					"█████████     ████   *████    *████*    ███████     ████████     ███      █████*   ████     █████████████████████████\n" + vectorColores.get(25) +
					"█████████    ████     ████*   ████     ███████     ████████     ████     █████    █████  ████████████████████████████\n" + vectorColores.get(26) +
					"████████     ███    *████     ███     ████████     ████████*    ███    *██████    ███████████████████████████████████\n" + vectorColores.get(27) +
					"███████    *████*   █████     ███     ███████     ████████     ████     █████    ████████████████████████████████████\n" + vectorColores.get(28) +
					"███████             ████     ███     ████████     ███████     ████     █████           ██████████████████████████████\n" + vectorColores.get(29) +
					"██████             ████    *████*    ███████     ████████*    ███     ██████            █████████████████████████████\n" + vectorColores.get(30) +
					"█████         *    ████     ███     ███████     ████████    *████     █████████████    *█████████████████████████████\n" + vectorColores.get(31) +
					"█████     ███     ████     ███    *████████*    ███████     ████    *██████████████     █████████████████████████████\n" + vectorColores.get(32) +
					"████    *████*   █████     ███     ███████     ████████     ████     ██████████████*   ██████████████████████████████\n" + vectorColores.get(33) +
					"████     ███     ████     ███    *███████      ███████     ████     █████    █████    ███████████████████████████████\n" + vectorColores.get(34) +
					"███     ████   *████    *████    ████████     ████████     ███     █████     █████    ███████████████████████████████\n" + vectorColores.get(35) +
					"██    *████     ████     ███     ███████    *████████      ███*    ████      ████    ████████████████████████████████\n" + vectorColores.get(36) +
					"██*    ███     ████      ██    *████████*    ████████      ██     █████*     ███    █████████████████████████████████\n" + vectorColores.get(37) +
					"█     ████*    ████      * *    ███████     █████████*           ██████*      *   * █████████████████████████████████\n" + vectorColores.get(38) +
					"██   █████    ██████           ████████    ███████████         █████████          ███████████████████████████████████\n" + vectorColores.get(39) +
					"█████████████████████████████████████████████████████████████████████████████████████████████████████████████████████\n" + reset;





			System.out.println(logo);
			if (offset > maxOffset) {
				break;
			}

			Thread.sleep(50);
			// borra la consola
			//System.out.flush();

			System.out.print("\033[H\033[2J");
			//Clear a = new Clear();

		}
	}

}