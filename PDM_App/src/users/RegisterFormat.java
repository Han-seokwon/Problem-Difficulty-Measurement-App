package users;

public class RegisterFormat{
	private String fieldName;
	private String condition;
	private String inputMsg;
	private String errMsg;
	
	public RegisterFormat(){};
	
	public RegisterFormat(String fieldName, String condition, String inputMsg, String errMsg ) {
		this.fieldName = fieldName;
		this.condition =  condition;
		this.inputMsg =  inputMsg;
		this.errMsg =  errMsg;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getCondition() {
		return condition;
	}

	public String getInputMsg() {
		return inputMsg;
	}

	public String getErrMsg() {
		return errMsg;
	}



}
