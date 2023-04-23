<template>
    <div class="container" id="container">
        <Loader :loading="showLoader"/>
        <TabsWrapper>
            <TabItem title="Dogs">
                <table class="table table-striped" id="contentTableDogs">
                    <table class="table table-striped" id="contentTableDogsList">
                        <thead>
                        <tr>
                            <input type="button" value="Update dog" class="btn btn-primary"
                                   @click="showModalClickedDogs(this.clickedDog, false, true, false, false)"/>
                            <input type="button" value="Create dog" class="btn btn-primary"
                                   @click="showModalClickedDogs(this.clickedDog, false, false, true, false)"/>
                            <input type="button" value="Delete dog" class="btn btn-primary"
                                   @click="showModalClickedDogs(this.clickedDog, false, false, false, true)"/>
                            <input type="button" value="Filter dogs" class="btn btn-primary"
                                   @click="showModalClickedDogs(this.clickedDog, true, false, false, false)"/>
                        </tr>
                        <tr>
                            <!-- the first table column is the list of all dogs -->
                            <th>
                                <h2 class="text-center"> Dogs List &#128203;</h2>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <div class="container">
                                  <span class="showItems">
                                    Show Items:
                                        <select @change="onChangeRecordsPerPage" v-model.number="recordsPerPage">
                                            <option :value="10">10</option>
                                            <option :value="20">20</option>
                                            <option :value="30">30</option>
                                            <option :value="40">40</option>
                                            <option :value="50">50</option>
                                            <option :value="100">100</option>
                                        </select>
                                  </span>
                                    <span>
                                    Go to Page <input type="text" v-model="enterPageNo"><button type="button"
                                                                                                @click.prevent="gotoPage">Go</button>
                                  </span>
                                    <span>
                                    <Pagination v-if="sortedDogs" :total-pages='this.totalPages'
                                                :per-page='this.recordsPerPage' :current-page='this.page'
                                                @pagechanged="onPageChange"/>
                                  </span>
                                    <table class="table table-striped" id="dogTable">
                                        <thead>
                                        <tr>
                                            <!--                                    dogs table header-->
                                            <th @click="sortList('id')"><h5 class="text-center"> Dog Id<span
                                                id="sid"
                                                style="opacity: 0"> ▲</span>
                                            </h5></th>
                                            <th @click="sortList('name')"><h5 class="text-center"> Dog Name<span
                                                id="sname"
                                                style="opacity: 0"> ▲</span>
                                            </h5></th>
                                            <th @click="sortList('breed')"><h5 class="text-center"> Dog Breed<span
                                                id="sbreed" style="opacity: 0"> ▲</span></h5></th>
                                            <th @click="sortList('age')"><h5 class="text-center"> Dog Age<span
                                                id="sage"
                                                style="opacity: 0"> ▲</span>
                                            </h5></th>
                                            <th @click="sortList('weight')"><h5 class="text-center"> Dog Weight<span
                                                id="sweight" style="opacity: 0"> ▲</span></h5></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <!--                                the dog entries-->
                                        <tr v-for="dog in sortedDogs" v-bind:key="dog.id"
                                            @click="showModalClickedDogs(dog, false, true, false, false)">
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
                        </tr>
                        </tbody>
                    </table>
                </table>
            </TabItem>

<!-- OWNERS -->
            <TabItem title="Owners">
                <table class="table table-striped" id="contentTableOwners">
                    <table class="table table-striped" id="contentTableOwnersList">
                        <thead>
                        <tr>
                            <input type="button" value="Update owner" class="btn btn-primary"
                                   @click="showModalClickedOwners(this.clickedOwner, false, true, false, false)"/>
                            <input type="button" value="Create owner" class="btn btn-primary"
                                   @click="showModalClickedOwners(this.clickedOwner, false, false, true, false)"/>
                            <input type="button" value="Delete owner" class="btn btn-primary"
                                   @click="showModalClickedOwners(this.clickedOwner, false, false, false, true)"/>
                            <input type="button" value="Filter owner" class="btn btn-primary"
                                   @click="showModalClickedOwners(this.clickedOwner, true, false, false, false)"/>
                        </tr>
                        <tr>
                            <!-- the first table column is the list of all owners -->
                            <th>
                                <h2 class="text-center"> Owners List &#128203;</h2>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <div class="container">
                                  <span class="showItems">
                                    Show Items:
                                        <select @change="onChangeRecordsPerPage" v-model.number="recordsPerPage">
                                            <option :value="10">10</option>
                                            <option :value="20">20</option>
                                            <option :value="30">30</option>
                                            <option :value="40">40</option>
                                            <option :value="50">50</option>
                                            <option :value="100">100</option>
                                        </select>
                                  </span>
                                    <span>
                                    Go to Page <input type="text" v-model="enterPageNo"><button type="button"
                                                                                                @click.prevent="gotoPage">Go</button>
                                  </span>
                                    <span>
                                    <Pagination v-if="sortedOwners" :total-pages='this.totalPages'
                                                :per-page='this.recordsPerPage' :current-page='this.page'
                                                @pagechanged="onPageChange"/>
                                  </span>
                                    <table class="table table-striped" id="dogTable">
                                        <thead>
                                        <tr>
                                            <!--                                    dogs table header-->
                                            <th @click="sortList('id')"><h5 class="text-center"> Owner Id<span
                                                id="sid"
                                                style="opacity: 0"> ▲</span>
                                            </h5></th>
                                            <th @click="sortList('first_name')"><h5 class="text-center"> Owner First Name<span
                                                id="sfname"
                                                style="opacity: 0"> ▲</span>
                                            </h5></th>
                                            <th @click="sortList('last_name')"><h5 class="text-center"> Owner Last Name<span
                                                id="slname"
                                                style="opacity: 0"> ▲</span>
                                            </h5></th>
                                            <th @click="sortList('job')"><h5 class="text-center"> Owner Job<span
                                                id="sjob" style="opacity: 0"> ▲</span></h5></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <!-- the owner entries -->
                                        <tr v-for="owner in sortedOwners" v-bind:key="owner.id"
                                            @click="showModalClickedDogs(owner, false, true, false, false)">
                                            <td> {{ owner.id }}</td>
                                            <td> {{ owner.first_name }}</td>
                                            <td> {{ owner.last_name }}</td>
                                            <td> {{ owner.job }}</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </table>
            </TabItem>

<!-- VETS -->
            <TabItem title="Vets">Vets</TabItem>

<!-- LICENSES -->
            <TabItem title="Licenses">Licenses</TabItem>

<!-- APPOINTMENTS -->
            <TabItem title="Appointments">Appointments</TabItem>
        </TabsWrapper>

<!-- DOGS -->
        <Modal ref="modal" v-show="showModal" @close-modal="updateAndCloseModalDogs()" :div-height="this.divHeight"
               @keydown.esc="updateAndCloseModalDogs()" tabindex="0" id="modal">
            <div v-show="this.updateDogClicked" class="add-form">
                <h6>DOG DETAILS</h6>
                <form v-on:submit.prevent="onSubmitUpdateDog" class="add-form">
                    <div class="form-control">
                        <label>ID: </label>
                        <span v-show="this.clickedDog.id !== ''" id="update_span_id">{{ this.clickedDog.id }}</span>
                        <input type="text" v-show="this.clickedDog.id === ''" id="update_input_id"
                               v-bind="this.clickedDog.id"
                               placeholder="Enter dog ID"/>
                    </div>
                    <div class="form-control">
                        <label>Name: </label>
                        <input type="text" v-model="clickedDog.name" id="name"
                               placeholder="Enter dog name"/>
                    </div>
                    <div class="form-control">
                        <label>Breed: </label>
                        <input type="text" v-model="clickedDog.breed" id="breed"
                               placeholder="Enter dog breed"/>
                    </div>
                    <div class="form-control">
                        <label>Age: </label>
                        <input type="text" v-model="clickedDog.age" id="age"
                               placeholder="Enter dog age"/>
                    </div>
                    <div class="form-control">
                        <label>Weight: </label>
                        <input type="text" v-model="clickedDog.weight" id="weight"
                               placeholder="Enter dog weight"/>
                    </div>
                    <hr>
                    <input type="submit" value="Update dog" class="btn btn-primary"/>
                </form>
            </div>
            <div v-show="this.createDogClicked" class="add-form">
                <h6>DOG DETAILS</h6>
                <form v-on:submit.prevent="onSubmitCreateDog" class="add-form">
                    <div class="form-control">
                        <label>Name: </label>
                        <input type="text" v-model="createFormDog.name" id="name"
                               placeholder="Enter dog name"/>
                    </div>
                    <div class="form-control">
                        <label>Breed: </label>
                        <input type="text" v-model="createFormDog.breed" id="breed"
                               placeholder="Enter dog breed"/>
                    </div>
                    <div class="form-control">
                        <label>Age: </label>
                        <input type="text" v-model="createFormDog.age" id="age"
                               placeholder="Enter dog age"/>
                    </div>
                    <div class="form-control">
                        <label>Weight: </label>
                        <input type="text" v-model="createFormDog.weight" id="weight"
                               placeholder="Enter dog weight"/>
                    </div>
                    <hr>
                    <input type="submit" value="Add dog" class="btn btn-primary"/>
                </form>
            </div>
            <div v-show="this.deleteDogClicked" class="add-form">
                <form v-on:submit.prevent="onSubmitDeleteDog" class="add-form">
                    <div class="form-control">
                        <label>ID: </label>
                        <input type="text" v-model="deleteFormDog.id" id="id"
                               placeholder="Enter dog ID"/>
                    </div>
                    <hr>
                    <input type="submit" value="Delete dog" class="btn btn-primary"/>
                </form>
            </div>
            <div v-show="this.listDogsClicked" class="add-form">
                <form v-on:submit.prevent="onSubmitShowFilteredDogs" class="add-form">
                    <div class="form-control">
                        <span>Filter: </span>
                        <input type="text" v-model="filterFormDog.weight" id="weight"
                               placeholder=" dogs heavier than"/>
                        <input type="submit" value="Filter dogs" class="btn btn-primary"/>
                    </div>
                </form>
            </div>
        </Modal>

<!-- OWNERS -->
<!--        <Modal ref="modal" v-show="showModal" @close-modal="updateAndCloseModalDogs()" :div-height="this.divHeight"-->
<!--               @keydown.esc="updateAndCloseModalDogs()" tabindex="0" id="modal">-->
<!--            <div v-show="this.updateOwnerClicked" class="add-form">-->
<!--                <h6>DOG DETAILS</h6>-->
<!--                <form v-on:submit.prevent="onSubmitUpdateDog" class="add-form">-->
<!--                    <div class="form-control">-->
<!--                        <label>ID: </label>-->
<!--                        <span v-show="this.clickedDog.id !== ''" id="update_span_id">{{ this.clickedDog.id }}</span>-->
<!--                        <input type="text" v-show="this.clickedDog.id === ''" id="update_input_id"-->
<!--                               v-bind="this.clickedDog.id"-->
<!--                               placeholder="Enter dog ID"/>-->
<!--                    </div>-->
<!--                    <div class="form-control">-->
<!--                        <label>Name: </label>-->
<!--                        <input type="text" v-model="clickedDog.name" id="name"-->
<!--                               placeholder="Enter dog name"/>-->
<!--                    </div>-->
<!--                    <div class="form-control">-->
<!--                        <label>Breed: </label>-->
<!--                        <input type="text" v-model="clickedDog.breed" id="breed"-->
<!--                               placeholder="Enter dog breed"/>-->
<!--                    </div>-->
<!--                    <div class="form-control">-->
<!--                        <label>Age: </label>-->
<!--                        <input type="text" v-model="clickedDog.age" id="age"-->
<!--                               placeholder="Enter dog age"/>-->
<!--                    </div>-->
<!--                    <div class="form-control">-->
<!--                        <label>Weight: </label>-->
<!--                        <input type="text" v-model="clickedDog.weight" id="weight"-->
<!--                               placeholder="Enter dog weight"/>-->
<!--                    </div>-->
<!--                    <hr>-->
<!--                    <input type="submit" value="Update dog" class="btn btn-primary"/>-->
<!--                </form>-->
<!--            </div>-->
<!--            <div v-show="this.createDogClicked" class="add-form">-->
<!--                <h6>DOG DETAILS</h6>-->
<!--                <form v-on:submit.prevent="onSubmitCreateDog" class="add-form">-->
<!--                    <div class="form-control">-->
<!--                        <label>Name: </label>-->
<!--                        <input type="text" v-model="createFormDog.name" id="name"-->
<!--                               placeholder="Enter dog name"/>-->
<!--                    </div>-->
<!--                    <div class="form-control">-->
<!--                        <label>Breed: </label>-->
<!--                        <input type="text" v-model="createFormDog.breed" id="breed"-->
<!--                               placeholder="Enter dog breed"/>-->
<!--                    </div>-->
<!--                    <div class="form-control">-->
<!--                        <label>Age: </label>-->
<!--                        <input type="text" v-model="createFormDog.age" id="age"-->
<!--                               placeholder="Enter dog age"/>-->
<!--                    </div>-->
<!--                    <div class="form-control">-->
<!--                        <label>Weight: </label>-->
<!--                        <input type="text" v-model="createFormDog.weight" id="weight"-->
<!--                               placeholder="Enter dog weight"/>-->
<!--                    </div>-->
<!--                    <hr>-->
<!--                    <input type="submit" value="Add dog" class="btn btn-primary"/>-->
<!--                </form>-->
<!--            </div>-->
<!--            <div v-show="this.deleteDogClicked" class="add-form">-->
<!--                <form v-on:submit.prevent="onSubmitDeleteDog" class="add-form">-->
<!--                    <div class="form-control">-->
<!--                        <label>ID: </label>-->
<!--                        <input type="text" v-model="deleteFormDog.id" id="id"-->
<!--                               placeholder="Enter dog ID"/>-->
<!--                    </div>-->
<!--                    <hr>-->
<!--                    <input type="submit" value="Delete dog" class="btn btn-primary"/>-->
<!--                </form>-->
<!--            </div>-->
<!--            <div v-show="this.listDogsClicked" class="add-form">-->
<!--                <form v-on:submit.prevent="onSubmitShowFilteredDogs" class="add-form">-->
<!--                    <div class="form-control">-->
<!--                        <span>Filter: </span>-->
<!--                        <input type="text" v-model="filterFormDog.weight" id="weight"-->
<!--                               placeholder=" dogs heavier than"/>-->
<!--                        <input type="submit" value="Filter dogs" class="btn btn-primary"/>-->
<!--                    </div>-->
<!--                </form>-->
<!--            </div>-->
<!--        </Modal>-->

    </div>
</template>

<script>

import DogService from '../services/DogService';
import axios from 'axios';
import Loader from './Loader.vue'
import Pagination from './Pagination.vue'
import TabsWrapper from './TabsWrapper.vue'
import TabItem from './TabItem.vue'
import Modal from "@/components/Modal.vue";
import OwnerService from "@/services/OwnerService";

export default {
    name: 'Dogs',
    components: {
        Modal,
        Loader,
        Pagination,
        TabsWrapper,
        TabItem
    },

    data() {
        return {
            divHeight: 440,
            showModal: false,

            listDogsClicked: false,
            updateDogClicked: false,
            createDogClicked: false,
            deleteDogClicked: false,

            listOwnersClicked: false,
            updateOwnerClicked: false,
            createOwnerClicked: false,
            deleteOwnerClicked: false,

            emptyDog: {
                id: '',
                name: '',
                breed: '',
                age: '',
                weight: ''
            },
            clickedDog: {
                id: '',
                name: '',
                breed: '',
                age: '',
                weight: ''
            },
            dogs: [],
            createFormDog: {
                name: '',
                breed: '',
                age: '',
                weight: ''
            },
            deleteFormDog: {
                id: ''
            },
            updateFormDog: {
                id: '',
                name: '',
                breed: '',
                age: '',
                weight: ''
            },
            filterFormDog: {
                weight: ''
            },

            emptyOwner: {
                id: '',
                first_name: '',
                last_name: '',
                job: ''
            },
            clickedOwner: {
                id: '',
                first_name: '',
                last_name: '',
                job: ''
            },
            owners: [],
            createFormOwner: {
                id: '',
                first_name: '',
                last_name: '',
                job: ''
            },
            deleteFormOwner: {
                id: '',
                first_name: '',
                last_name: '',
                job: ''
            },
            updateFormOwner: {
                id: '',
                first_name: '',
                last_name: '',
                job: ''
            },

            currentSort: 'id',
            currentSortDir: 'desc',
            showLoader: false,
            page: 1,
            totalPages: 0,
            totalRecords: 0,
            recordsPerPage: 10,
            enterPageNo: '',
        };
    },

    mounted() {
        this.sortList('id');
    },

    computed: {
        sortedDogs: function () {
            return this.dogs.sort((a, b) => {
                let modifier = 1;
                if (this.currentSortDir === 'desc') modifier = -1;
                if (a[this.currentSort] < b[this.currentSort]) return -1 * modifier;
                if (a[this.currentSort] > b[this.currentSort]) return 1 * modifier;
                return 0;
            });
        },

        sortedOwners: function () {
            return this.owners.sort((a, b) => {
                let modifier = 1;
                if (this.currentSortDir === 'desc') modifier = -1;
                if (a[this.currentSort] < b[this.currentSort]) return -1 * modifier;
                if (a[this.currentSort] > b[this.currentSort]) return 1 * modifier;
                return 0;
            });
        }
    },

    methods: {
        showModalClickedDogs(dog, lClicked, uClicked, cClicked, dClicked) {
            this.clickedDog = dog;
            this.listDogsClicked = lClicked;
            this.updateDogClicked = uClicked;
            this.createDogClicked = cClicked;
            this.deleteDogClicked = dClicked;
            this.showModal = true;
            if (this.listDogsClicked) {
                this.divHeight = 150;
            } else if (this.deleteDogClicked) {
                this.divHeight = 200;
            } else {
                this.divHeight = 440;
            }
        },

        showModalClickedOwners(owner, lClicked, uClicked, cClicked, dClicked) {
            this.clickedOwner = owner;
            this.listOwnersClicked = lClicked;
            this.updateOwnerClicked = uClicked;
            this.createOwnerClicked = cClicked;
            this.deleteOwnerClicked = dClicked;
            this.showModal = true;
            if (this.listOwnersClicked) {
                this.divHeight = 150;
            } else if (this.deleteOwnerClicked) {
                this.divHeight = 200;
            } else {
                this.divHeight = 440;
            }
        },

        updateAndCloseModalDogs() {
            this.showModal = false
            document.getElementById('update_input_id').value = '';
            this.clickedDog = this.emptyDog;
        },

        updateAndCloseModalOwners() {
            this.showModal = false
            document.getElementById('update_input_id').value = '';
            this.clickedOwner = this.emptyOwner;
        },

        getDogs() {
            DogService.getDogs().then((response) => {
                this.dogs = response.data.dogs;
                this.totalRecords = response.data.totalItems;
                this.totalPages = response.data.totalPages;
                this.page = response.data.currentPage + 1;
            });
        },

        getOwners() {
            OwnerService.getOwners().then((response) => {
                this.owners = response.data.owners;
                this.totalRecords = response.data.totalItems;
                this.totalPages = response.data.totalPages;
                this.page = response.data.currentPage + 1;
            });
        },

        onSubmitCreateDog(e) {
            e.preventDefault()
            if (!this.createFormDog.name) {
                alert('Please Add a Name')
                return
            }

            this.showLoader = true
            axios.post(DogService.getUrl(), this.createFormDog)
                .then((res) => {
                    window.location.reload()
                })
                .catch((error) => {
                    alert(JSON.stringify(error.response.data));
                    console.log(JSON.stringify(error.response.data));
                }).finally(() => {
                this.showLoader = false
            });
            this.createFormDog.name = ' '
            this.createFormDog.breed = ' '
            this.createFormDog.age = ' '
            this.createFormDog.weight = ' '
        },

        onSubmitCreateOwner(e) {
            e.preventDefault()
            if (!this.createFormOwner.first_name) {
                alert('Please Add a First Name')
                return
            }

            this.showLoader = true
            axios.post(OwnerService.getUrl(), this.createFormOwner)
                .then((res) => {
                    window.location.reload()
                })
                .catch((error) => {
                    alert(JSON.stringify(error.response.data));
                    console.log(JSON.stringify(error.response.data));
                }).finally(() => {
                this.showLoader = false
            });
            this.createFormOwner.first_name = ' '
            this.createFormOwner.last_name = ' '
            this.createFormOwner.job = ' '
        },

        onSubmitDeleteDog(e) {
            e.preventDefault()
            if (!this.deleteFormDog.id) {
                alert('Please Add an ID')
                return
            }

            this.showLoader = true
            axios.delete(DogService.getUrl() + '/' + this.deleteFormDog.id, this.deleteFormDog)
                .then((res) => {
                    window.location.reload()
                })
                .catch((error) => {
                    alert(JSON.stringify(error.response.data));
                    console.log(JSON.stringify(error.response.data));
                }).finally(() => {
                this.showLoader = false
            });
            this.deleteFormDog.id = ' '
        },

        onSubmitDeleteOwner(e) {
            e.preventDefault()
            if (!this.deleteFormOwner.id) {
                alert('Please Add an ID')
                return
            }

            this.showLoader = true
            axios.delete(OwnerService.getUrl() + '/' + this.deleteFormOwner.id, this.deleteFormOwner)
                .then((res) => {
                    window.location.reload()
                })
                .catch((error) => {
                    alert(JSON.stringify(error.response.data));
                    console.log(JSON.stringify(error.response.data));
                }).finally(() => {
                this.showLoader = false
            });
            this.deleteFormOwner.id = ' '
        },

        onSubmitUpdateDog(e) {
            e.preventDefault()

            // use local const, we don't want changes to affect for due to reactivity
            const localDog = {
                id: '',
                name: '',
                breed: '',
                age: '',
                weight: ''
            }

            localDog.id = this.clickedDog.id;
            localDog.name = this.clickedDog.name;
            localDog.breed = this.clickedDog.breed;
            localDog.age = this.clickedDog.age;
            localDog.weight = this.clickedDog.weight;

            if (!localDog.id) {
                localDog.id = document.getElementById('update_input_id').value;
            }

            if (!localDog.id) {
                localDog.id = document.getElementById('update_span_id').textContent;
            }

            if (!localDog.id) {
                alert('Please Add an ID');

                this.clickedDog = this.emptyDog;

                window.location.reload();

                return;
            }
            if (!localDog.name) {
                alert('Please Add a name');
                this.clickedDog = this.emptyDog;

                window.location.reload();

                return;
            }

            this.showLoader = true;
            axios.put(DogService.getUrl() + '/' + localDog.id, localDog)
                .then((res) => {
                    window.location.reload();
                })
                .catch((error) => {
                    alert(JSON.stringify(error.response.data));
                    console.log(JSON.stringify(error.response.data));
                    window.location.reload();
                }).finally(() => {
                this.clickedDog = this.emptyDog;
                this.showLoader = false;
            });
        },

        onSubmitUpdateOwner(e) {
            e.preventDefault()

            // use local const, we don't want changes to affect for due to reactivity
            const localOwner = {
                id: '',
                first_name: '',
                last_name: '',
                job: ''
            }

            localOwner.id = this.clickedOwner.id;
            localOwner.first_name = this.clickedOwner.first_name;
            localOwner.last_name = this.clickedOwner.last_name;
            localOwner.job = this.clickedOwner.job;

            if (!localOwner.id) {
                localOwner.id = document.getElementById('update_input_id').value;
            }

            if (!localOwner.id) {
                localOwner.id = document.getElementById('update_span_id').textContent;
            }

            if (!localOwner.id) {
                alert('Please Add an ID');

                this.clickedOwner = this.emptyOwner;

                window.location.reload();

                return;
            }
            if (!localOwner.first_name) {
                alert('Please Add a First Name');
                this.clickedOwner = this.emptyOwner;

                window.location.reload();

                return;
            }

            if (!localOwner.last_name) {
                alert('Please Add a Last Name');
                this.clickedOwner = this.emptyOwner;

                window.location.reload();

                return;
            }

            this.showLoader = true;
            axios.put(OwnerService.getUrl() + '/' + localOwner.id, localOwner)
                .then((res) => {
                    window.location.reload();
                })
                .catch((error) => {
                    alert(JSON.stringify(error.response.data));
                    console.log(JSON.stringify(error.response.data));
                    window.location.reload();
                }).finally(() => {
                this.clickedOwner = this.emptyOwner;
                this.showLoader = false;
            });
        },

        onSubmitShowFilteredDogs(e) {
            e.preventDefault()
            if (this.filterFormDog.weight.length === 0) {
                this.loadPageDogs();
            } else {
                this.showLoader = true
                axios.get(DogService.getUrl() + '/dogs-heavier-than-given-number/' + this.filterFormDog.weight)
                    .then((response) => {
                        this.dogs = response.data;
                    }).finally(() => {
                    this.showLoader = false;
                });
            }
        },

        loadPageDogs() {
            this.showLoader = true
            const updatedPage = this.page - 1;
            this.showLoader = true;
            axios.get(DogService.getUrl() + '?page=' + updatedPage + '&size=' + this.recordsPerPage)
                .then((response) => {
                    this.showLoader = false
                    this.dogs = response.data.dogs;
                    this.totalRecords = response.data.totalItems;
                    this.totalPages = response.data.totalPages;
                    this.page = response.data.currentPage + 1;
                }).finally(() => {
                this.showLoader = false;
            });
        },

        onPageChange(page) {
            this.page = page
            this.loadPageDogs()
        },

        onChangeRecordsPerPage() {
            this.loadPageDogs()
        },

        gotoPage() {
            if (!isNaN(parseInt(this.enterPageNo))) {
                this.page = parseInt(this.enterPageNo)
                this.loadPageDogs()
            }
        },

        sortList: function (s) {
            // remove icon of current sort icon
            document.getElementById('s' + this.currentSort).style.opacity = "0";
            //if s == current sort, reverse
            if (s === this.currentSort) {
                this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc';
            } else {
                this.currentSortDir = 'asc';
            }
            this.currentSort = s;
            document.getElementById('s' + this.currentSort).innerHTML = this.currentSortDir === 'asc' ? ' ▲' : ' ▼';
            document.getElementById('s' + this.currentSort).style.opacity = "1";
        },

        updateUpdateForm: function (dog) {
            this.updateFormDog.id = dog.id;
            this.updateFormDog.name = dog.name;
            this.updateFormDog.breed = dog.breed;
            this.updateFormDog.age = dog.age;
            this.updateFormDog.weight = dog.weight;
        }

    },
    created() {
        this.loadPageDogs();
        this.escapeHandler = (e) => {
            if (e.key === 'Escape' && this.showModal) {
                this.updateAndCloseModalDogs();
            }
        }

        document.addEventListener('keydown', this.escapeHandler);
    },
    unmounted() {
        document.removeEventListener('keydown', this.escapeHandler);
    }
}

</script>

<style>
#dogTable {
    width: max-content;
    table-layout: auto;
}

/*#bgcont:before {*/
/*    content: ' ';*/
/*    display: flex;*/
/*    position: absolute;*/
/*    left: 0;*/
/*    top: 0;*/
/*    width: 100%;*/
/*    height: 100%;*/
/*    opacity: 0.1;*/
/*    background-image: url('../assets/ShihTzu Mascot.png');*/
/*    background-repeat: no-repeat;*/
/*    background-position: 50% 0;*/
/*    background-size: cover;*/
/*    z-index: -100;*/
/*}*/

/*#bgcont {*/
/*    content: ' ';*/
/*    display: flex;*/
/*    position: absolute;*/
/*    left: 0;*/
/*    top: 0;*/
/*    width: 100%;*/
/*    height: 100%;*/
/*    opacity: 0.7;*/
/*    background-image: url('../assets/ShihTzu Mascot.png');*/
/*    background-repeat: no-repeat;*/
/*    background-position: 50% 0;*/
/*    background-size: cover;*/
/*    position: relative;*/
/*}*/

#container {
    position: relative;
}

h5:hover {
    cursor: pointer;
    background: rgb(229, 255, 211);
}

.showItems {
    display: inline-block;
    margin-left: -35px;

    li {
        list-style-type: none;
        display: inline-block;
        margin-left: 10px;
    }
}
</style>