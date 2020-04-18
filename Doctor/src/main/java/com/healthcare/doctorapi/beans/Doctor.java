package com.healthcare.doctorapi.beans;

public class Doctor {
	//Doctor Class
	private Integer Doctor_ID;
	private String Doctor_Name;
	private String Doctor_Specialization;
	private String Doctor_MedicalRegistrationNo;
	private String Doctor_ContactNo;
	private String Doctor_Address;
	private String Doctor_Email;
	private String Doctor_NIC;

	public Doctor() {
		
	}
	
	public Doctor(Integer Doctor_ID, String Doctor_Name, String Doctor_Specialization, String Doctor_MedicalRegistrationNo, String Doctor_ContactNo, String Doctor_Address, String Doctor_Email, String Doctor_NIC) {
			this.Doctor_ID  = Doctor_ID;
			this.Doctor_Name = Doctor_Name;
			this.Doctor_Specialization = Doctor_Specialization;
			this.Doctor_MedicalRegistrationNo = Doctor_MedicalRegistrationNo;
			this.Doctor_ContactNo = Doctor_ContactNo;
			this.Doctor_Address = Doctor_Address;
			this.Doctor_Email = Doctor_Email;
			this.Doctor_NIC = Doctor_NIC;
			
		}

		public Integer getId() {
			return Doctor_ID;
		}

		public void setId(Integer Doctor_ID) {
			this.Doctor_ID = Doctor_ID;
		}

		public String getName() {
			return Doctor_Name;
		}

		public void setName(String Doctor_Name) {
			this.Doctor_Name = Doctor_Name;
		}

		public String getDoctor_Specialization() {
			return Doctor_Specialization;
		}

		public void setDoctor_Specialization(String doctor_Specialization) {
			Doctor_Specialization = doctor_Specialization;
		}

		public String getDoctor_MedicalRegistrationNo() {
			return Doctor_MedicalRegistrationNo;
		}

		public void setDoctor_MedicalRegistrationNo(String doctor_MedicalRegistrationNo) {
			Doctor_MedicalRegistrationNo = doctor_MedicalRegistrationNo;
		}

		public String getDoctor_ContactNo() {
			return Doctor_ContactNo;
		}

		public void setDoctor_ContactNo(String doctor_ContactNo) {
			Doctor_ContactNo = doctor_ContactNo;
		}

		public String getDoctor_Address() {
			return Doctor_Address;
		}

		public void setDoctor_Address(String doctor_Address) {
			Doctor_Address = doctor_Address;
		}

		public String getDoctor_NIC() {
			return Doctor_NIC;
		}

		public void setDoctor_NIC(String doctor_NIC) {
			Doctor_NIC = doctor_NIC;
		}

		public String getDoctor_Email() {
			return Doctor_Email;
		}

		public void setDoctor_Email(String doctor_Email) {
			Doctor_Email = doctor_Email;
		}
		
		@Override
		public String toString() {
			return "Doctor [Doctor_ID=" + Doctor_ID + ", "
					+ "Doctor_Name=" + Doctor_Name + ", "
					+ "Doctor_Specialization=" + Doctor_Specialization + ", "
					+ "Doctor_MedicalRegistrationNo=" + Doctor_MedicalRegistrationNo + ", "
					+ "Doctor_ContactNo=" + Doctor_ContactNo + ", "
					+ "Doctor_Address =" + Doctor_Address + "," + "Doctor_Email =" + Doctor_Email + "," + "Doctor_NIC =" + Doctor_NIC +"]";
		}
	}
