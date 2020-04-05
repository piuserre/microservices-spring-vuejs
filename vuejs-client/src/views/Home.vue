<template>
  <div class="container">
    <div class="rent-container">
      <div class="card">
        <div class="card-header">
          <div class="row">
            <div class="col col-xs-6">
              <h3 class="panel-title">All Cars</h3>
            </div>
          </div>
        </div>

        <div class="card-body">
          <table class="table table-striped">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Brand</th>
                <th scope="col">Model</th>
                <th scope="col">Year</th>
                <th scope="col">Daily Prize</th>
                <th scope="col">Kilometers</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in cars" v-bind:key="item.id">
                <th scope="row"></th>
                <td>{{item.brand}}</td>
                <td>{{item.name}}</td>
                <td>{{item.year}}</td>
                <td>{{item.prize}}</td>
                <td>{{item.km}}</td>
                <td>
                  <button class="btn btn-success" @click="rent(item)">Rent</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import UserService from "../services/user.service";
import CarService from "../services/car.service";
import { User } from "../models/user";
import { Rent } from "../models/rent";
export default {
  name: "home",
  data() {
    return {
      cars: [],
      currentUser: new User()
    };
  },
  created() {
    UserService.currentUser.subscribe(data => {
      this.currentUser = data;
    });
    CarService.findAllCars().then(data => {
      this.cars = data.data;
    });
  },
  methods: {
    _rent(car) {
      console.log(car);
      
      /* CarService.createRent(rent).then(
        data => {
          //You can get warning, if you don't use paremeters.
          console.log(data);
          this.$store.dispatch("success", "Rent successfull.");
        },
        error => {
          console.log(error);
          this.$store.dispatch("error", "Unexpected error occurred.");
        }
      ); */
    },
    rent(item) {
      console.log(item);
      if (!this.currentUser) {
        this.$store.dispatch("error", "You should sign in to rent a car.");
        return;
      }
      var rent = new Rent(this.currentUser.id, item);
      console.log(rent);
      
      localStorage.setItem("detailCar", JSON.stringify(item));
      this.$router.push({ name: "rent", params: { id: item.id } });
    }
  }
};
</script>
<style scoped>
</style>
