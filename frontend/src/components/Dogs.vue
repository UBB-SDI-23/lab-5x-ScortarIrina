<template>
    <div class = "container">

        <h1 class = "text-center"> Dogs List</h1>

        <table class = "table table-striped" id="dogTable">
            <thead>
            <tr>
                <th> Dog Id</th>
                <th> Dog Name</th>
                <th> Dog Breed</th>
                <th> Dog Age</th>
                <th> Dog Weight</th>
            </tr>

            </thead>
            <tbody>
            <tr v-for="dog in dogs" v-bind:key="dog.id">
                <td> {{dog.id }}</td>
                <td> {{dog.name }}</td>
                <td> {{dog.breed}}</td>
                <td> {{dog.age}}</td>
                <td> {{dog.weight}}</td>
            </tr>
            </tbody>
        </table>
    </div>

    <form @submit="onSubmit" class="add-form" method="post">
        <div class="form-control">
            <label>Name</label>
            <input type="text" v-model="name" name="name" placeholder="Enter dog name" />
        </div>
        <div class="form-control">
            <label>Breed</label>
            <input type="text" v-model="breed" name="breed" placeholder="Enter dog breed" />
        </div>
        <div class="form-control">
            <label>Age</label>
            <input type="text" v-model="age" name="age" placeholder="Enter dog age" />
        </div>
        <div class="form-control">
            <label>Weight</label>
            <input type="text" v-model="weight" name="weight" placeholder="Enter dog weight" />
        </div>
        <input type="submit" value="Add dog" class="btn btn-block" />
    </form>
</template>

<script>

import DogService from '../services/DogService';

export default {
    name: 'Dogs',
    data(){
        return {
            dogs: []
        }
    },
    methods: {
        getDogs(){
            DogService.getDogs().then((response) => {
                this.dogs = response.data;
            });
        },

        onSubmit(e){
            e.preventDefault()
            if(!this.name){
                alert('Please Add a Name')
                return
            }
            const newInformation = {
                name : this.name,
                breed : this.breed,
                age : this.age,
                weight : this.weight
            }
            this.$emit('add-information', newInformation)
            this.name = ' '
            this.breed = ' '
            this.age = ' '
            this.weight = ' '
        }
    },
    created() {
        this.getDogs();
    }
}

</script>

<style>
    #dogTable {
        width: max-content;
        table-layout: auto;
    }
</style>