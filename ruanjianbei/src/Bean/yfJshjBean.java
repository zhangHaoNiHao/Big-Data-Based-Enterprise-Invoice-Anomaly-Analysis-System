package Bean;

public class yfJshjBean {

	private String nsr_id;
	private double jshj;
	private String kpyf;
	public String getNsr_id() {
		return nsr_id;
	}
	public void setNsr_id(String nsr_id) {
		this.nsr_id = nsr_id;
	}
	public double getJshj() {
		return jshj;
	}
	public void setJshj(double jshj) {
		this.jshj = jshj;
	}
	public String getKpyf() {
		return kpyf;
	}
	public void setKpyf(String kpyf) {
		this.kpyf = kpyf;
	}
	@Override
	public String toString() {
		return "yfJshjBean [nsr_id=" + nsr_id + ", jshj=" + jshj + ", kpyf=" + kpyf + "]";
	}
	public yfJshjBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public yfJshjBean(String nsr_id, double jshj, String kpyf) {
		super();
		this.nsr_id = nsr_id;
		this.jshj = jshj;
		this.kpyf = kpyf;
	}
	
}
