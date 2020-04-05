import axios  from 'axios';

let API_URL = 'http://localhost:8087/';

class CarService {
    createRent(rent) {
        return axios.post(API_URL + 'rent', JSON.stringify(rent),
        {headers: {'Content-Type':'application/json; charset=UTF-8'}});
    }

    filterRents(userId) {
        return axios.get(API_URL + 'rent/' + userId, {headers: {'Content-Type':'application/json; charset=UTF-8'}});
    }

    findAllCars() {
        return axios.get(API_URL + 'all', {headers: {'Content-Type':'application/json; charset=UTF-8'}});
    }

    validateRent(carId, startDate, endDate){
        return axios.get(API_URL + 'rent/validate/' + carId + '/' + startDate + '/' + endDate, {headers: {'Content-Type':'application/json; charset=UTF-8'}})
    }
}
export default new CarService();