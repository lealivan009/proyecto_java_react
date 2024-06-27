interface AbstractPrescription {
    patientName: string,
    medicamentDescription: string,
    posology: string,
    durationTratament: number
}

export interface Prescription extends AbstractPrescription{
    emissionDate: Date
}

export interface RegisterPrescription extends AbstractPrescription{
}