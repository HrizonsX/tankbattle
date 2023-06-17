package stage.body.scores;

public class Score {
	private int normalTank;
	private int fattyTank;
	private int fastTank;
	private int tool;
	
	public Score() {
		this.normalTank = 0;
		this.fastTank = 0;
		this.fattyTank = 0;
		this.tool = 0;
	}

	public int getNormalTank() {
		return normalTank;
	}

	public void setNormalTank(int normalTank) {
		this.normalTank = normalTank;
	}

	public int getFattyTank() {
		return fattyTank;
	}

	public void setFattyTank(int fattyTank) {
		this.fattyTank = fattyTank;
	}

	public int getFastTank() {
		return fastTank;
	}

	public void setFastTank(int fastTank) {
		this.fastTank = fastTank;
	}

	public int getTool() {
		return tool;
	}

	public void setTool(int tool) {
		this.tool = tool;
	}

}
