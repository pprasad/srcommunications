
public class Main {

	public static void main(String[] args) {

		PrinterService printerService = new PrinterService();
		
		System.out.println(printerService.getPrinters());
				
		//print some stuff
		printerService.printString("Microsoft Print to PDF", "\n\n testing testing 1 2 3eeeee \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

		// cut that paper!
		byte[] cutP = new byte[] { 0x1d, 'V', 1 };

		printerService.printBytes("Microsoft Print to PDF", cutP);
	
	}

}