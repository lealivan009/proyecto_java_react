
const timeRegex = /^([01]?[0-9]|2[0-3]):[0-5][0-9]$/;

export function dateFormat(date: Date){
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
} 

interface ValidateScheduleProps {
    startTime: string,
    endTime: string,
    consultingDate: Date,
}

export function validateSchedule({startTime, endTime, consultingDate}: ValidateScheduleProps){
    const start = new Date(startTime);
    const end = new Date(endTime);
    return (consultingDate <= start && consultingDate >= end);
}

export const validateTimeFormat = (time: string) =>{
    return timeRegex.test(time);
}