<template>
<div class="container">
  <header class="jumbotron">
    <h1 class="display-5">
      You want to rent <strong>{{currentCar.name}}</strong>
    </h1>
  </header>

  <div >
    <div class="card card-container">
      <ValidationObserver v-slot="{ handleSubmit, valid }" ref="observer">
        <form name="form" @submit.prevent="handleSubmit(handleRent)">
          <h3>Your Rent Info</h3>

          <div class="form-group col-md-6">
            <label for="brand">Brand</label>
            <input
                type="text"
                class="form-control"
                name="brand"
                v-model="currentCar.brand"
                readonly
            />
          </div>
          <div class="form-group col-md-6">
            <label for="name">Model</label>
            <input
                type="text"
                class="form-control"
                name="name"
                v-model="currentCar.name"
                readonly
            />
          </div>
          <div class="form-group col-md-6">
            <label for="year">Year</label>
            <input
                type="text"
                class="form-control"
                name="year"
                v-model="currentCar.year"
                readonly
            />
          </div>
          <div class="form-group col-md-6">
            <label for="prize">Daily Prize</label>
            <input
                type="text"
                class="form-control"
                name="prize"
                v-model="currentCar.prize"
                readonly
            />
          </div>
          <div class="form-group col-md-6">
            <label for="km">Km</label>
            <input
                type="text"
                class="form-control"
                name="km"
                v-model="currentCar.km"
                readonly
            />
          </div>
          <ValidationProvider name="startdate" rules="required" v-slot="{ errors }"> 
            <div class="form-group col-md-6">
              <label for="model">Start Date</label>
              <input
                type="date"
                class="form-control"
                name="startdate"
                v-model="startDate"
                @change="changedData"
              />
              <div
                class="alert alert-danger"
                role="alert"
                v-show="errors[0]"
              >{{errors[0]}}</div>
            </div>
          </ValidationProvider>
          <ValidationProvider name="enddate" rules="required" v-slot="{ errors }"> 
            <div class="form-group col-md-6">
              <label for="model">End Date</label>
              <input
                type="date"
                class="form-control"
                name="enddate"
                v-model="endDate"
                @change="changedData"
              />
              <div
                class="alert alert-danger"
                role="alert"
                v-show="errors[0]"
              >{{errors[0]}}</div>
            </div>
          </ValidationProvider>
          <div class="col-md-3">
            <label for="totalprice">Total Prize: </label>
            <span
              name="totalprice"
              readonly
            > {{totalprize}} $</span>
          </div>
          <div class="form-group col-md-6">
            <button class="btn btn-primary btn-block" v-show="!confirmRent" :disabled="!valid">
              <span class="spinner-border spinner-border-sm" v-show="loading"></span>
              <span>Check Availability</span>
            </button>
            <button class="btn btn-success btn-block" @click="processRent" v-show="confirmRent">
              <span class="spinner-border spinner-border-sm" v-show="loading"></span>
              <span>Rent!</span>
            </button>
          </div>
        </form>
      </ValidationObserver>  
    </div>
  </div>
  
</div>
</template>

<script>
import CarService from '../services/car.service';
import UserService from '../services/user.service';
import UtilsService from '../services/utils.service';
import { Rent } from '../models/rent';
import { ValidationObserver, ValidationProvider } from "vee-validate";
export default {
  name: 'detail',
  components: {
    ValidationObserver,
    ValidationProvider
  },
  data() {
    return {
      currentUser: null,
      carId: null,
      currentCar: null,
      startDate: null,
      endDate: null,
      loading: false,
      confirmRent: false,
      totalprize: 0,
    };
  },
  created() {
    this.currentUser = UserService.currentUserValue;

    if(!this.currentUser)
      this.$router.push('/home');
    
    this.carId = this.$route.params.id;
    this.currentCar = JSON.parse(localStorage.getItem('detailCar'));

    if (this.carId!=this.currentCar.id)
      this.$router.push('/home');
  },
  methods:{
    handleRent() {
      this.loading = true;
      CarService.validateRent(this.carId, this.startDate, this.endDate).then(
        data => {
          this.loading = false;
          if (data.data)
            this.confirmRent=true;
          else
            this.$store.dispatch("error", "This is car is not available for this period.");
        },
        () => {
          this.loading = false;
          this.$store.dispatch("error", "Username or password is not valid.");
          this.$router.push("/home");
        }
      ); 
    },
    changedData(){

      if (!this.startDate || !this.endDate)
        this.confirmRent=false;
      if (this.startDate && this.endDate && this.startDate>this.endDate){
        this.$store.dispatch("error", "Start date must be before end date.");
        console.log(this.$refs.observer);
        if (this.confirmRent){
          this.confirmRent=false;
        }
      }
      else if (this.startDate && this.endDate){
        this.$store.dispatch('clear');
        this.totalprize = UtilsService.calculateTotalPrice(this.startDate, this.endDate, this.currentCar.prize);
      }
        
    },
    processRent(){
      var rent = new Rent(this.currentUser.id, this.currentCar, this.startDate, this.endDate);
      console.log(JSON.stringify(rent));
      CarService.createRent(rent).then(
        () => {
          this.$store.dispatch("success", "Rent successfull.");
          setTimeout(() => {
              this.$router.push("/profile");
            }, 1000);
        },
        () => {
          this.$store.dispatch("error", "Error during rent process.");
        }
      );
    }
  }
}
</script>
<style scoped>
</style>