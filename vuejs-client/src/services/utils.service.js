class UtilsService {
    calculateTotalPrice(start, end, prize) {
        // To set two dates to two variables 
        var date1 = new Date(start); 
        var date2 = new Date(end); 
        // To calculate the no. of days between two dates 
        var diff = ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24))+1; 

        return prize * diff;
    }
}
export default new UtilsService();