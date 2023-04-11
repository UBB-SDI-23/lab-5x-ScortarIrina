<template>
    <div className="container" id="container">
        <div id="bgcont">
<!--            the whole page is a table-->
            <table className="table table-striped" id="contentTable">
                <thead>
<!--                the first table column is the list of all dogs-->
                <th><h2 className="text-center"> Dogs List</h2></th>
<!--                the second column is the form for adding a dog-->
                <th><h2 className="text-center"> Create Dog</h2></th>
<!--                the third column is the form for deleting a dog-->
                <th><h2 className="text-center"> Delete Dog</h2></th>
<!--                the fourth column is the form for updating a dog-->
                <th><h2 className="text-center"> Update Dog</h2></th>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <div className="container">
                            <table className="table table-striped" id="dogTable">
                                <thead>
                                <tr>
<!--                                    dogs table header-->
                                    <th><h5 className="text-center"> Dog Id</h5></th>
                                    <th><h5 className="text-center"> Dog Name</h5></th>
                                    <th><h5 className="text-center"> Dog Breed</h5></th>
                                    <th><h5 className="text-center"> Dog Age</h5></th>
                                    <th><h5 className="text-center"> Dog Weight</h5></th>
                                </tr>
                                </thead>
                                <tbody>
<!--                                the dog entries-->
                                <tr v-for="dog in dogs" v-bind:key="dog.id">
                                    <td> {{ dog.id }}</td>
                                    <td> {{ dog.name }}</td>
                                    <td> {{ dog.breed }}</td>
                                    <td> {{ dog.age }}</td>
                                    <td> {{ dog.weight }}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </td>
                    <td>
<!--                        the ADD dog form-->
                        <form v-on:submit.prevent="onSubmitCreate" className="add-form">
                            <div className="form-control">
                                <label>Name</label>
                                <input type="text" v-model="createForm.name" id="name" placeholder="Enter dog name"/>
                            </div>
                            <div className="form-control">
                                <label>Breed</label>
                                <input type="text" v-model="createForm.breed" id="breed" placeholder="Enter dog breed"/>
                            </div>
                            <div className="form-control">
                                <label>Age</label>
                                <input type="text" v-model="createForm.age" id="age" placeholder="Enter dog age"/>
                            </div>
                            <div className="form-control">
                                <label>Weight</label>
                                <input type="text" v-model="createForm.weight" id="weight"
                                       placeholder="Enter dog weight"/>
                            </div>
                            <hr>
                            <input type="submit" value="Add dog" className="btn btn-primary"/>
                        </form>
                    </td>
                    <td>
<!--                        the DELETE dog form-->
                        <form v-on:submit.prevent="onSubmitDelete" className="add-form">
                            <div className="form-control">
                                <label>ID</label>
                                <input type="text" v-model="deleteForm.id" id="id" placeholder="Enter dog ID"/>
                            </div>
                            <hr>
                            <input type="submit" value="Delete dog" className="btn btn-primary"/>
                        </form>
                    </td>
                    <td>
<!--                        the UPDATE dog form-->
                        <form v-on:submit.prevent="onSubmitUpdate" className="add-form">
                            <div className="form-control">
                                <label>ID</label>
                                <input type="text" v-model="updateForm.id" id="id" placeholder="Enter dog ID"/>
                            </div>
                            <div className="form-control">
                                <label>Name</label>
                                <input type="text" v-model="updateForm.name" id="name" placeholder="Enter dog name"/>
                            </div>
                            <div className="form-control">
                                <label>Breed</label>
                                <input type="text" v-model="updateForm.breed" id="breed" placeholder="Enter dog breed"/>
                            </div>
                            <div className="form-control">
                                <label>Age</label>
                                <input type="text" v-model="updateForm.age" id="age" placeholder="Enter dog age"/>
                            </div>
                            <div className="form-control">
                                <label>Weight</label>
                                <input type="text" v-model="updateForm.weight" id="weight"
                                       placeholder="Enter dog weight"/>
                            </div>
                            <hr>
                            <input type="submit" value="Update dog" className="btn btn-primary"/>
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>

import DogService from '../services/DogService';
import axios, {Axios, AxiosError} from 'axios';

export default {
    name: 'Dogs',
    data() {
        return {
            dogs: [],
            createForm: {
                name: '',
                breed: '',
                age: '',
                weight: ''
            },
            deleteForm: {
                id: ''
            },
            updateForm: {
                id: '',
                name: '',
                breed: '',
                age: '',
                weight: ''
            }
        }
    },
    methods: {
        getDogs() {
            DogService.getDogs().then((response) => {
                this.dogs = response.data;
            });
        },

        onSubmitCreate(e) {
            e.preventDefault()
            if (!this.createForm.name) {
                alert('Please Add a Name')
                return
            }

            axios.post(DogService.getUrl(), this.createForm)
                .then((res) => {
                    window.location.reload()
                })
                .catch((error) => {
                    alert(JSON.stringify(error.response.data));
                    console.log(JSON.stringify(error.response.data));
                }).finally(() => {
                //Perform action in always
            });
            this.createForm.name = ' '
            this.createForm.breed = ' '
            this.createForm.age = ' '
            this.createForm.weight = ' '
        },

        onSubmitDelete(e) {
            e.preventDefault()
            if (!this.deleteForm.id) {
                alert('Please Add an ID')
                return
            }

            axios.delete(DogService.getUrl() + '/' + this.deleteForm.id, this.deleteForm)
                .then((res) => {
                    window.location.reload()
                })
                .catch((error) => {
                    alert(JSON.stringify(error.response.data));
                    console.log(JSON.stringify(error.response.data));
                }).finally(() => {
                //Perform action in always
            });
            this.deleteForm.id = ' '
        },

        onSubmitUpdate(e) {
            e.preventDefault()
            if (!this.updateForm.id) {
                alert('Please Add an ID')
                return
            }
            if (!this.updateForm.name) {
                alert('Please Add a name')
                return
            }

            axios.put(DogService.getUrl() + '/' + this.updateForm.id, this.updateForm)
                .then((res) => {
                    window.location.reload()
                })
                .catch((error) => {
                    alert(JSON.stringify(error.response.data));
                    console.log(JSON.stringify(error.response.data));
                }).finally(() => {
                //Perform action in always
            });
            this.updateForm.id = ' '
            this.updateForm.name = ' '
            this.updateForm.breed = ' '
            this.updateForm.age = ' '
            this.updateForm.weight = ' '
        },
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

#bgcont:before {
    content: ' ';
    display: block;
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    opacity: 0.1;
    background-image: url('../assets/ShihTzu Mascot.png');
    background-repeat: no-repeat;
    background-position: 50% 0;
    background-size: cover;
}

#bgcont {
    position: relative;
}

#container {
    position: relative;
}
</style>