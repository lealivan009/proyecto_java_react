interface AbstractAppointment {
    consultingReason: string,
    consultingDate: Date,
    medicalId: string
}

export interface RegisterAppointment extends AbstractAppointment {
    patient_name: string,
    userId: string
}

export interface UpdateAppointment extends AbstractAppointment {
}

export interface Appointment {
    id: string
    patient_name: string,
    consultingReason: string,
    consultingDate: Date
}