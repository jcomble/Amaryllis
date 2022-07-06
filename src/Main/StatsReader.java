package Main;

public class StatsReader {
	private int numero_familier;
	private double exp;
	private int version;
	private int pv;
	private int pm;
	
	public StatsReader(int numero_familier, int exp, int version, int pv, int pm) {
		this.numero_familier = numero_familier;
		this.exp = (double) exp;
		this.version = version;
		this.pv = pv;
		this.pm = pm;
	}
	
	public String getstats() {
		if (version == 0) {
			return "-";
		}
		double c = 1;
		double b = Math.log(9) / Math.log(4269./20.);
		double a = 1. / Math.pow(20, b); 
		int niveau = (int) (a * Math.pow(exp, b) + c);
		return "niv. " + String.valueOf(niveau) + "\n" + String.valueOf(pv) + "/20 PV\n" + String.valueOf(pm) +"/50 PM";
	}
	
}
