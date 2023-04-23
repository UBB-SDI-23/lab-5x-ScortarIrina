<!--<template>-->
<!--    <div class="container" id="container">-->
<!--        <Loader :loading="showLoader"/>-->
<!--        <TabsWrapper>-->
<!--            <TabItem title="Owners">-->
<!--                <table class="table table-striped" id="contentTableOwners">-->
<!--                    <table class="table table-striped" id="contentTableOwnersList">-->
<!--                        <thead>-->
<!--                        <tr>-->
<!--                            <input type="button" value="Update owner" class="btn btn-primary"-->
<!--                                   @click="showModalClicked(this.clickedDog, false, true, false, false)"/>-->
<!--                            <input type="button" value="Create owner" class="btn btn-primary"-->
<!--                                   @click="showModalClicked(this.clickedDog, false, false, true, false)"/>-->
<!--                            <input type="button" value="Delete owner" class="btn btn-primary"-->
<!--                                   @click="showModalClicked(this.clickedDog, false, false, false, true)"/>-->
<!--                        </tr>-->
<!--                        <tr>-->
<!--                            &lt;!&ndash; the first table column is the list of all owners &ndash;&gt;-->
<!--                            <th>-->
<!--                                <h2 class="text-center"> Owners List &#128203;</h2>-->
<!--                            </th>-->
<!--                        </tr>-->
<!--                        </thead>-->
<!--                        <tbody>-->
<!--                        <tr>-->
<!--                            <td>-->
<!--                                <div class="container">-->
<!--                                  <span class="showItems">-->
<!--                                    Show Items:-->
<!--                                        <select @change="onChangeRecordsPerPage" v-model.number="recordsPerPage">-->
<!--                                            <option :value="10">10</option>-->
<!--                                            <option :value="20">20</option>-->
<!--                                            <option :value="30">30</option>-->
<!--                                            <option :value="40">40</option>-->
<!--                                            <option :value="50">50</option>-->
<!--                                            <option :value="100">100</option>-->
<!--                                        </select>-->
<!--                                  </span>-->
<!--                                    <span>-->
<!--                                    Go to Page <input type="text" v-model="enterPageNo"><button type="button"-->
<!--                                                                                                @click.prevent="gotoPage">Go</button>-->
<!--                                  </span>-->
<!--                                    <span>-->
<!--                                    <Pagination v-if="sortedOwners" :total-pages='this.totalPages'-->
<!--                                                :per-page='this.recordsPerPage' :current-page='this.page'-->
<!--                                                @pagechanged="onPageChange"/>-->
<!--                                  </span>-->
<!--                                    <table class="table table-striped" id="ownerTable">-->
<!--                                        <thead>-->
<!--                                        <tr>-->
<!--                                            &lt;!&ndash; dogs table header &ndash;&gt;-->
<!--                                            <th @click="sortList('id')"><h5 class="text-center"> Owner Id<span-->
<!--                                                    id="sid"-->
<!--                                                    style="opacity: 0"> ▲</span>-->
<!--                                            </h5></th>-->
<!--                                            <th @click="sortList('fname')"><h5 class="text-center"> Owner First-->
<!--                                                Name<span-->
<!--                                                        id="sfname"-->
<!--                                                        style="opacity: 0"> ▲</span>-->
<!--                                            </h5></th>-->
<!--                                            <th @click="sortList('lname')"><h5 class="text-center"> Owner Last Name<span-->
<!--                                                    id="slname"-->
<!--                                                    style="opacity: 0"> ▲</span>-->
<!--                                            </h5></th>-->
<!--&lt;!&ndash;                                            <th @click="sortList('age')"><h5 class="text-center"> Owner Age<span&ndash;&gt;-->
<!--&lt;!&ndash;                                                    id="sage"&ndash;&gt;-->
<!--&lt;!&ndash;                                                    style="opacity: 0"> ▲</span>&ndash;&gt;-->
<!--&lt;!&ndash;                                            </h5></th>&ndash;&gt;-->
<!--&lt;!&ndash;                                            <th @click="sortList('gender')"><h5 class="text-center"> Owner Gender<span&ndash;&gt;-->
<!--&lt;!&ndash;                                                    id="sgender"&ndash;&gt;-->
<!--&lt;!&ndash;                                                    style="opacity: 0"> ▲</span>&ndash;&gt;-->
<!--&lt;!&ndash;                                            </h5></th>&ndash;&gt;-->
<!--                                            <th @click="sortList('job')"><h5 class="text-center"> Owner Job<span-->
<!--                                                    id="sjob"-->
<!--                                                    style="opacity: 0"> ▲</span>-->
<!--                                            </h5></th>-->
<!--                                        </tr>-->
<!--                                        </thead>-->
<!--                                        <tbody>-->
<!--                                        &lt;!&ndash; the owner entries &ndash;&gt;-->
<!--                                        <tr v-for="owner in sortedOwners" v-bind:key="owner.id"-->
<!--                                            @click="showModalClicked(owner, false, true, false, false)">-->
<!--                                            <td> {{ owner.id }}</td>-->
<!--                                            <td> {{ owner.first_name }}</td>-->
<!--                                            <td> {{ owner.last_name }}</td>-->
<!--                                            <td> {{ owner.job }}</td>-->
<!--                                        </tr>-->
<!--                                        </tbody>-->
<!--                                    </table>-->
<!--                                </div>-->
<!--                            </td>-->
<!--                        </tr>-->
<!--                        </tbody>-->
<!--                    </table>-->
<!--                </table>-->
<!--            </TabItem>-->
<!--            <TabItem title="Owners">Owners</TabItem>-->
<!--            <TabItem title="Vets">Vets</TabItem>-->
<!--            <TabItem title="Licenses">Licenses</TabItem>-->
<!--            <TabItem title="Appointments">Appointments</TabItem>-->
<!--        </TabsWrapper>-->
<!--        <Modal ref="modal" v-show="showModal" @close-modal="updateAndCloseModal()" :div-height="this.divHeight"-->
<!--               @keydown.esc="updateAndCloseModal()" tabindex="0" id="modal">-->
<!--            <div v-show="this.updateDogClicked" class="add-form">-->
<!--                <h6>DOG DETAILS</h6>-->
<!--                <form v-on:submit.prevent="onSubmitUpdate" class="add-form">-->
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
<!--                <form v-on:submit.prevent="onSubmitCreate" class="add-form">-->
<!--                    <div class="form-control">-->
<!--                        <label>Name: </label>-->
<!--                        <input type="text" v-model="createForm.name" id="name"-->
<!--                               placeholder="Enter dog name"/>-->
<!--                    </div>-->
<!--                    <div class="form-control">-->
<!--                        <label>Breed: </label>-->
<!--                        <input type="text" v-model="createForm.breed" id="breed"-->
<!--                               placeholder="Enter dog breed"/>-->
<!--                    </div>-->
<!--                    <div class="form-control">-->
<!--                        <label>Age: </label>-->
<!--                        <input type="text" v-model="createForm.age" id="age"-->
<!--                               placeholder="Enter dog age"/>-->
<!--                    </div>-->
<!--                    <div class="form-control">-->
<!--                        <label>Weight: </label>-->
<!--                        <input type="text" v-model="createForm.weight" id="weight"-->
<!--                               placeholder="Enter dog weight"/>-->
<!--                    </div>-->
<!--                    <hr>-->
<!--                    <input type="submit" value="Add dog" class="btn btn-primary"/>-->
<!--                </form>-->
<!--            </div>-->
<!--            <div v-show="this.deleteDogClicked" class="add-form">-->
<!--                <form v-on:submit.prevent="onSubmitDelete" class="add-form">-->
<!--                    <div class="form-control">-->
<!--                        <label>ID: </label>-->
<!--                        <input type="text" v-model="deleteForm.id" id="id"-->
<!--                               placeholder="Enter dog ID"/>-->
<!--                    </div>-->
<!--                    <hr>-->
<!--                    <input type="submit" value="Delete dog" class="btn btn-primary"/>-->
<!--                </form>-->
<!--            </div>-->
<!--            <div v-show="this.listDogsClicked" class="add-form">-->
<!--                <form v-on:submit.prevent="onSubmitShowFiltered" class="add-form">-->
<!--                    <div class="form-control">-->
<!--                        <span>Filter: </span>-->
<!--                        <input type="text" v-model="filterForm.weight" id="weight"-->
<!--                               placeholder=" dogs heavier than"/>-->
<!--                        <input type="submit" value="Filter dogs" class="btn btn-primary"/>-->
<!--                    </div>-->
<!--                </form>-->
<!--            </div>-->
<!--        </Modal>-->
<!--    </div>-->
<!--</template>-->

<!--<script>-->

<!--import DogService from '../services/DogService';-->
<!--import axios from 'axios';-->
<!--import Loader from './Loader.vue'-->
<!--import Pagination from './Pagination.vue'-->
<!--import TabsWrapper from './TabsWrapper.vue'-->
<!--import TabItem from './TabItem.vue'-->
<!--import Modal from "@/components/Modal.vue";-->

<!--export default {-->
<!--    name: 'Dogs',-->
<!--    components: {-->
<!--        Modal,-->
<!--        Loader,-->
<!--        Pagination,-->
<!--        TabsWrapper,-->
<!--        TabItem-->
<!--    },-->

<!--    data() {-->
<!--        return {-->
<!--            divHeight: 440,-->
<!--            showModal: false,-->
<!--            listDogsClicked: false,-->
<!--            updateDogClicked: false,-->
<!--            createDogClicked: false,-->
<!--            deleteDogClicked: false,-->
<!--            emptyDog: {-->
<!--                id: '',-->
<!--                name: '',-->
<!--                breed: '',-->
<!--                age: '',-->
<!--                weight: ''-->
<!--            },-->
<!--            clickedDog: {-->
<!--                id: '',-->
<!--                name: '',-->
<!--                breed: '',-->
<!--                age: '',-->
<!--                weight: ''-->
<!--            },-->
<!--            dogs: [],-->
<!--            createForm: {-->
<!--                name: '',-->
<!--                breed: '',-->
<!--                age: '',-->
<!--                weight: ''-->
<!--            },-->
<!--            deleteForm: {-->
<!--                id: ''-->
<!--            },-->
<!--            updateForm: {-->
<!--                id: '',-->
<!--                name: '',-->
<!--                breed: '',-->
<!--                age: '',-->
<!--                weight: ''-->
<!--            },-->
<!--            filterForm: {-->
<!--                weight: ''-->
<!--            },-->
<!--            currentSort: 'id',-->
<!--            currentSortDir: 'desc',-->
<!--            showLoader: false,-->
<!--            page: 1,-->
<!--            totalPages: 0,-->
<!--            totalRecords: 0,-->
<!--            recordsPerPage: 10,-->
<!--            enterPageNo: '',-->
<!--        };-->
<!--    },-->

<!--    mounted() {-->
<!--        this.sortList('id');-->
<!--    },-->

<!--    computed: {-->
<!--        sortedDogs: function () {-->
<!--            return this.dogs.sort((a, b) => {-->
<!--                let modifier = 1;-->
<!--                if (this.currentSortDir === 'desc') modifier = -1;-->
<!--                if (a[this.currentSort] < b[this.currentSort]) return -1 * modifier;-->
<!--                if (a[this.currentSort] > b[this.currentSort]) return 1 * modifier;-->
<!--                return 0;-->
<!--            });-->
<!--        }-->
<!--    },-->

<!--    methods: {-->
<!--        showModalClicked(dog, lClicked, uClicked, cClicked, dClicked) {-->
<!--            this.clickedDog = dog;-->
<!--            this.listDogsClicked = lClicked;-->
<!--            this.updateDogClicked = uClicked;-->
<!--            this.createDogClicked = cClicked;-->
<!--            this.deleteDogClicked = dClicked;-->
<!--            this.showModal = true;-->
<!--            if (this.listDogsClicked) {-->
<!--                this.divHeight = 150;-->
<!--            } else if (this.deleteDogClicked) {-->
<!--                this.divHeight = 200;-->
<!--            } else {-->
<!--                this.divHeight = 440;-->
<!--            }-->
<!--        },-->

<!--        updateAndCloseModal() {-->
<!--            this.showModal = false-->
<!--            document.getElementById('update_input_id').value = '';-->
<!--            this.clickedDog = this.emptyDog;-->
<!--        },-->

<!--        getDogs() {-->
<!--            DogService.getDogs().then((response) => {-->
<!--                this.dogs = response.data.dogs;-->
<!--                this.totalRecords = response.data.totalItems;-->
<!--                this.totalPages = response.data.totalPages;-->
<!--                this.page = response.data.currentPage + 1;-->
<!--            });-->
<!--        },-->

<!--        onSubmitCreate(e) {-->
<!--            e.preventDefault()-->
<!--            if (!this.createForm.name) {-->
<!--                alert('Please Add a Name')-->
<!--                return-->
<!--            }-->

<!--            this.showLoader = true-->
<!--            axios.post(DogService.getUrl(), this.createForm)-->
<!--                .then((res) => {-->
<!--                    window.location.reload()-->
<!--                })-->
<!--                .catch((error) => {-->
<!--                    alert(JSON.stringify(error.response.data));-->
<!--                    console.log(JSON.stringify(error.response.data));-->
<!--                }).finally(() => {-->
<!--                this.showLoader = false-->
<!--            });-->
<!--            this.createForm.name = ' '-->
<!--            this.createForm.breed = ' '-->
<!--            this.createForm.age = ' '-->
<!--            this.createForm.weight = ' '-->
<!--        },-->

<!--        onSubmitDelete(e) {-->
<!--            e.preventDefault()-->
<!--            if (!this.deleteForm.id) {-->
<!--                alert('Please Add an ID')-->
<!--                return-->
<!--            }-->

<!--            this.showLoader = true-->
<!--            axios.delete(DogService.getUrl() + '/' + this.deleteForm.id, this.deleteForm)-->
<!--                .then((res) => {-->
<!--                    window.location.reload()-->
<!--                })-->
<!--                .catch((error) => {-->
<!--                    alert(JSON.stringify(error.response.data));-->
<!--                    console.log(JSON.stringify(error.response.data));-->
<!--                }).finally(() => {-->
<!--                this.showLoader = false-->
<!--            });-->
<!--            this.deleteForm.id = ' '-->
<!--        },-->

<!--        onSubmitUpdate(e) {-->
<!--            e.preventDefault()-->

<!--            // use local const, we don't want changes to affect for due to reactivity-->
<!--            const localDog = {-->
<!--                id: '',-->
<!--                name: '',-->
<!--                breed: '',-->
<!--                age: '',-->
<!--                weight: ''-->
<!--            }-->

<!--            localDog.id = this.clickedDog.id;-->
<!--            localDog.name = this.clickedDog.name;-->
<!--            localDog.breed = this.clickedDog.breed;-->
<!--            localDog.age = this.clickedDog.age;-->
<!--            localDog.weight = this.clickedDog.weight;-->

<!--            if (!localDog.id) {-->
<!--                localDog.id = document.getElementById('update_input_id').value;-->
<!--            }-->

<!--            if (!localDog.id) {-->
<!--                localDog.id = document.getElementById('update_span_id').textContent;-->
<!--            }-->

<!--            if (!localDog.id) {-->
<!--                alert('Please Add an ID');-->

<!--                this.clickedDog = this.emptyDog;-->

<!--                window.location.reload();-->

<!--                return;-->
<!--            }-->
<!--            if (!localDog.name) {-->
<!--                alert('Please Add a name');-->
<!--                this.clickedDog = this.emptyDog;-->

<!--                window.location.reload();-->

<!--                return;-->
<!--            }-->

<!--            this.showLoader = true;-->
<!--            axios.put(DogService.getUrl() + '/' + localDog.id, localDog)-->
<!--                .then((res) => {-->
<!--                    window.location.reload();-->
<!--                })-->
<!--                .catch((error) => {-->
<!--                    alert(JSON.stringify(error.response.data));-->
<!--                    console.log(JSON.stringify(error.response.data));-->
<!--                    window.location.reload();-->
<!--                }).finally(() => {-->
<!--                this.clickedDog = this.emptyDog;-->
<!--                this.showLoader = false;-->
<!--            });-->
<!--        },-->

<!--        onSubmitShowFiltered(e) {-->
<!--            e.preventDefault()-->
<!--            if (this.filterForm.weight.length === 0) {-->
<!--                this.loadPage();-->
<!--            } else {-->
<!--                this.showLoader = true-->
<!--                axios.get(DogService.getUrl() + '/dogs-heavier-than-given-number/' + this.filterForm.weight)-->
<!--                    .then((response) => {-->
<!--                        this.dogs = response.data;-->
<!--                    }).finally(() => {-->
<!--                    this.showLoader = false;-->
<!--                });-->
<!--            }-->
<!--        },-->

<!--        loadPage() {-->
<!--            this.showLoader = true-->
<!--            const updatedPage = this.page - 1;-->
<!--            this.showLoader = true;-->
<!--            axios.get(DogService.getUrl() + '?page=' + updatedPage + '&size=' + this.recordsPerPage)-->
<!--                .then((response) => {-->
<!--                    this.showLoader = false-->
<!--                    this.dogs = response.data.dogs;-->
<!--                    this.totalRecords = response.data.totalItems;-->
<!--                    this.totalPages = response.data.totalPages;-->
<!--                    this.page = response.data.currentPage + 1;-->
<!--                }).finally(() => {-->
<!--                this.showLoader = false;-->
<!--            });-->
<!--        },-->

<!--        onPageChange(page) {-->
<!--            this.page = page-->
<!--            this.loadPage()-->
<!--        },-->
<!--        onChangeRecordsPerPage() {-->
<!--            this.loadPage()-->
<!--        },-->
<!--        gotoPage() {-->
<!--            if (!isNaN(parseInt(this.enterPageNo))) {-->
<!--                this.page = parseInt(this.enterPageNo)-->
<!--                this.loadPage()-->
<!--            }-->
<!--        },-->

<!--        sortList: function (s) {-->
<!--            // remove icon of current sort icon-->
<!--            document.getElementById('s' + this.currentSort).style.opacity = "0";-->
<!--            //if s == current sort, reverse-->
<!--            if (s === this.currentSort) {-->
<!--                this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc';-->
<!--            } else {-->
<!--                this.currentSortDir = 'asc';-->
<!--            }-->
<!--            this.currentSort = s;-->
<!--            document.getElementById('s' + this.currentSort).innerHTML = this.currentSortDir === 'asc' ? ' ▲' : ' ▼';-->
<!--            document.getElementById('s' + this.currentSort).style.opacity = "1";-->
<!--        },-->

<!--        updateUpdateForm: function (dog) {-->
<!--            this.updateForm.id = dog.id;-->
<!--            this.updateForm.name = dog.name;-->
<!--            this.updateForm.breed = dog.breed;-->
<!--            this.updateForm.age = dog.age;-->
<!--            this.updateForm.weight = dog.weight;-->
<!--        }-->

<!--    },-->
<!--    created() {-->
<!--        this.loadPage();-->
<!--        this.escapeHandler = (e) => {-->
<!--            if (e.key === 'Escape' && this.showModal) {-->
<!--                this.updateAndCloseModal();-->
<!--            }-->
<!--        }-->

<!--        document.addEventListener('keydown', this.escapeHandler);-->
<!--    },-->
<!--    unmounted() {-->
<!--        document.removeEventListener('keydown', this.escapeHandler);-->
<!--    }-->
<!--}-->

<!--</script>-->

<!--<style>-->
<!--#dogTable {-->
<!--    width: max-content;-->
<!--    table-layout: auto;-->
<!--}-->

<!--/*#bgcont:before {*/-->
<!--/*    content: ' ';*/-->
<!--/*    display: flex;*/-->
<!--/*    position: absolute;*/-->
<!--/*    left: 0;*/-->
<!--/*    top: 0;*/-->
<!--/*    width: 100%;*/-->
<!--/*    height: 100%;*/-->
<!--/*    opacity: 0.1;*/-->
<!--/*    background-image: url('../assets/ShihTzu Mascot.png');*/-->
<!--/*    background-repeat: no-repeat;*/-->
<!--/*    background-position: 50% 0;*/-->
<!--/*    background-size: cover;*/-->
<!--/*    z-index: -100;*/-->
<!--/*}*/-->

<!--/*#bgcont {*/-->
<!--/*    content: ' ';*/-->
<!--/*    display: flex;*/-->
<!--/*    position: absolute;*/-->
<!--/*    left: 0;*/-->
<!--/*    top: 0;*/-->
<!--/*    width: 100%;*/-->
<!--/*    height: 100%;*/-->
<!--/*    opacity: 0.7;*/-->
<!--/*    background-image: url('../assets/ShihTzu Mascot.png');*/-->
<!--/*    background-repeat: no-repeat;*/-->
<!--/*    background-position: 50% 0;*/-->
<!--/*    background-size: cover;*/-->
<!--/*    position: relative;*/-->
<!--/*}*/-->

<!--#container {-->
<!--    position: relative;-->
<!--}-->

<!--h5:hover {-->
<!--    cursor: pointer;-->
<!--    background: rgb(229, 255, 211);-->
<!--}-->

<!--.showItems {-->
<!--    display: inline-block;-->
<!--    margin-left: -35px;-->

<!--    li {-->
<!--        list-style-type: none;-->
<!--        display: inline-block;-->
<!--        margin-left: 10px;-->
<!--    }-->
<!--}-->
<!--</style>-->