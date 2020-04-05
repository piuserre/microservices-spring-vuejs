<template>
<div class="container">
    <header class="jumbotron">
        <h1 class="display-5">
            <strong>{{currentUser.name}}</strong> Your Rents
        </h1>
    </header>

    <div class="transaction-container">
        <div class="card">

            <div class="card-body">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Brand</th>
                            <th scope="col">Model</th>
                            <th scope="col">Start Rent</th>
                            <th scope="col">End Rent</th>
                            <th scope="col">Total Prize</th>
                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(item) in rents" v-bind:key="item.id">
                            <th scope="row"></th>
                            <td>{{item.car.brand}}</td>
                            <td>{{item.car.name}}</td>
                            <td>{{item.startRent}}</td>
                            <td>{{item.endRent}}</td>
                            <td>{{calculateTotalPrize(item.startRent, item.endRent, item.car.prize)}}</td>

                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</template>
<script>
import UserService from '../services/user.service';
import CarService from '../services/car.service';
import UtilsService from '../services/utils.service';
export default {
    name: 'profile',
    data() {
        return {
            currentUser: null,
            rents: [],
        };
    },
    created() {
        this.currentUser = UserService.currentUserValue;
        if(!this.currentUser){
            this.$router.push('/login');
        }
        console.log('retrieve rents of user '+this.currentUser.id);
        CarService.filterRents(this.currentUser.id).then(rents => {
            this.rents = rents.data;
        });

    },
    methods: {
        calculateTotalPrize(start, end, prize){
            return UtilsService.calculateTotalPrice(start, end, prize);
        },
        logOut() {
            UserService.logOut().then(() => {
                this.$router.push('/login');
            });
        }
    }
}
</script>
<style scoped>
</style>