export function dateFormat(date: Date){
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
} 

export function validateSchedule(startTime, endTime, consultingDate){
    const start = new Date(startTime);
    const end = new Date(endTime);
    return (consultingDate <= start && consultingDate >= end);

}