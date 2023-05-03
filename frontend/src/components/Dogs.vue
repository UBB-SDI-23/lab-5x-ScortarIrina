<template>
    <div class="container" id="container">
        <Loader :loading="showLoader"/>

        <TabsWrapper class="allTabs">
            <TabItem title="Dogs" class="tabs">
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
                                    Go to Page <input type="text" v-model="enterPageNo">
                                        <button type="button" @click.prevent="gotoPageDogs">Go</button>
                                  </span>
                                    <span>
                                    <Pagination v-if="sortedDogs" :total-pages='this.totalPages'
                                                :per-page='this.recordsPerPage' :current-page='this.page'
                                                @pagechanged="onPageChangeDogs"/>
                                  </span>
                                    <table class="table table-striped" id="dogTable">
                                        <thead>
                                        <tr>
                                            <th @click="sortList('id')">
                                                <h5 class="text-center"> Id
                                                    <span
                                                            id="sid"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                            <th @click="sortList('name')">
                                                <h5 class="text-center"> Name
                                                    <span
                                                            id="sname"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                            <th @click="sortList('breed')">
                                                <h5 class="text-center"> Breed
                                                    <span
                                                            id="sbreed"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                            <th @click="sortList('age')">
                                                <h5 class="text-center"> Age
                                                    <span
                                                            id="sage"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                            <th @click="sortList('weight')">
                                                <h5 class="text-center"> Weight
                                                    <span
                                                            id="sweight"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                            <th @click="sortList('number_of_vets')">
                                                <h5 class="text-center"> #visited vets
                                                    <span
                                                            id="snumber_of_vets"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr v-for="dog in sortedDogs" v-bind:key="dog.id"
                                            @click="showModalClickedDogs(dog, false, true, false, false)">
                                            <td> {{ dog.id }}</td>
                                            <td> {{ dog.name }}</td>
                                            <td> {{ dog.breed }}</td>
                                            <td> {{ dog.age }}</td>
                                            <td> {{ dog.weight }}</td>
                                            <td> {{ dog.number_of_vets }}</td>
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

            <TabItem title="Owners" class="tabs">
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
                        </tr>
                        <tr>

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
                                    Go to Page <input type="text" v-model="enterPageNo">
                                        <button type="button" @click.prevent="gotoPageOwners">Go</button>
                                  </span>
                                    <span>
                                    <Pagination v-if="sortedOwners" :total-pages='this.totalPages'
                                                :per-page='this.recordsPerPage' :current-page='this.page'
                                                @pagechanged="onPageChangeOwners"/>
                                  </span>
                                    <table class="table table-striped" id="ownerTable">
                                        <thead>
                                        <tr>
                                            <th @click="sortList('id')">
                                                <h5 class="text-center"> Id
                                                    <span
                                                            id="sid"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                            <th @click="sortList('first_name')">
                                                <h5 class="text-center"> First Name
                                                    <span
                                                            id="sfirst_name"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                            <th @click="sortList('last_name')">
                                                <h5 class="text-center"> Last Name
                                                    <span
                                                            id="slast_name"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                            <th @click="sortList('job')">
                                                <h5 class="text-center"> Job
                                                    <span
                                                            id="sjob"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                            <th @click="sortList('number_of_dogs')">
                                                <h5 class="text-center"> #dogs
                                                    <span
                                                            id="snumber_of_dogs"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr v-for="owner in sortedOwners" v-bind:key="owner.id"
                                            @click="showModalClickedOwners(owner, false, true, false, false)">
                                            <td> {{ owner.id }}</td>
                                            <td> {{ owner.first_name }}</td>
                                            <td> {{ owner.last_name }}</td>
                                            <td> {{ owner.job }}</td>
                                            <td> {{ owner.number_of_dogs }}</td>
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

            <TabItem title="Vets" class="tabs">
                <table class="table table-striped" id="contentTableVets">
                    <table class="table table-striped" id="contentTableVetsList">
                        <thead>
                        <tr>
                            <input type="button" value="Update vet" class="btn btn-primary"
                                   @click="showModalClickedVets(this.clickedVet, false, true, false, false)"/>
                            <input type="button" value="Create vet" class="btn btn-primary"
                                   @click="showModalClickedVets(this.clickedVet, false, false, true, false)"/>
                            <input type="button" value="Delete vet" class="btn btn-primary"
                                   @click="showModalClickedVets(this.clickedVet, false, false, false, true)"/>
                        </tr>
                        <tr>

                            <th>
                                <h2 class="text-center"> Vets List &#128203;</h2>
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
                                    Go to Page <input type="text" v-model="enterPageNo">
                                        <button type="button" @click.prevent="gotoPageVets">Go</button>
                                  </span>
                                    <span>
                                    <Pagination v-if="sortedVets" :total-pages='this.totalPages'
                                                :per-page='this.recordsPerPage' :current-page='this.page'
                                                @pagechanged="onPageChangeVets"/>
                                  </span>
                                    <table class="table table-striped" id="vetTable">
                                        <thead>
                                        <tr>
                                            <th @click="sortList('id')">
                                                <h5 class="text-center"> Id
                                                    <span
                                                            id="sid"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                            <th @click="sortList('first_name')">
                                                <h5 class="text-center"> First Name
                                                    <span
                                                            id="sfname"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                            <th @click="sortList('last_name')">
                                                <h5 class="text-center"> Last Name
                                                    <span
                                                            id="slname"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                            <th @click="sortList('specialty')">
                                                <h5 class="text-center"> Specialty
                                                    <span
                                                            id="sspecialty"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                            <th @click="sortList('number_of_dogs')">
                                                <h5 class="text-center"> #consulted dogs
                                                    <span
                                                            id="snumber_of_dogs"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr v-for="vet in sortedVets" v-bind:key="vet.id"
                                            @click="showModalClickedVets(vet, false, true, false, false)">
                                            <td> {{ vet.id }}</td>
                                            <td> {{ vet.first_name }}</td>
                                            <td> {{ vet.last_name }}</td>
                                            <td> {{ vet.specialty }}</td>
                                            <td> {{ vet.number_of_dogs }}</td>
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

            <TabItem title="Licenses" class="tabs">
                <table class="table table-striped" id="contentTableLicenses">
                    <table class="table table-striped" id="contentTableVetsList">
                        <thead>
                        <tr>
                            <input type="button" value="Update license" class="btn btn-primary"
                                   @click="showModalClickedLicenses(this.clickedLicense, false, true, false, false)"/>
                            <input type="button" value="Create license" class="btn btn-primary"
                                   @click="showModalClickedLicenses(this.clickedLicense, false, false, true, false)"/>
                            <input type="button" value="Delete license" class="btn btn-primary"
                                   @click="showModalClickedLicenses(this.clickedLicense, false, false, false, true)"/>
                        </tr>
                        <tr>

                            <th>
                                <h2 class="text-center"> Licenses List &#128203;</h2>
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
                                    Go to Page <input type="text" v-model="enterPageNo">
                                        <button type="button" @click.prevent="gotoPageLicenses">Go</button>
                                  </span>
                                    <span>
                                    <Pagination v-if="sortedLicenses" :total-pages='this.totalPages'
                                                :per-page='this.recordsPerPage' :current-page='this.page'
                                                @pagechanged="onPageChangeLicenses"/>
                                  </span>
                                    <table class="table table-striped" id="licenseTable">
                                        <thead>
                                        <tr>
                                            <th @click="sortList('id')">
                                                <h5 class="text-center"> Id
                                                    <span
                                                            id="sid"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                            <th @click="sortList('serial_number')">
                                                <h5 class="text-center"> Serial Number
                                                    <span
                                                            id="sserialno"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                            <th @click="sortList('university')">
                                                <h5 class="text-center"> University
                                                    <span
                                                            id="suniversity"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                            <th @click="sortList('city')">
                                                <h5 class="text-center"> City
                                                    <span
                                                            id="scity"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr v-for="license in sortedLicenses" v-bind:key="license.id"
                                            @click="showModalClickedLicenses(license, false, true, false, false)">
                                            <td> {{ license.id }}</td>
                                            <td> {{ license.serial_number }}</td>
                                            <td> {{ license.university }}</td>
                                            <td> {{ license.city }}</td>
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

            <TabItem title="Appointments" class="tabs">
                <table class="table table-striped" id="contentTableAppointments">
                    <table class="table table-striped" id="contentTableAppointmentsList">
                        <thead>
                        <tr>
                            <input type="button" value="Update appointment" class="btn btn-primary"
                                   @click="showModalClickedAppointments(this.clickedAppointment, false, true, false, false)"/>
                            <input type="button" value="Create appointment" class="btn btn-primary"
                                   @click="showModalClickedAppointments(this.clickedAppointment, false, false, true, false)"/>
                            <input type="button" value="Delete appointment" class="btn btn-primary"
                                   @click="showModalClickedAppointments(this.clickedAppointment, false, false, false, true)"/>
                        </tr>
                        <tr>
                            <th>
                                <h2 class="text-center"> Appointments List &#128203;</h2>
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
                                    Go to Page <input type="text" v-model="enterPageNo">
                                        <button type="button" @click.prevent="gotoPageAppointments">Go</button>
                                  </span>
                                    <span>
                                    <Pagination v-if="appointments" :total-pages='this.totalPages'
                                                :per-page='this.recordsPerPage' :current-page='this.page'
                                                @pagechanged="onPageChangeAppointments"/>
                                  </span>
                                    <table class="table table-striped" id="appointmentTable">
                                        <thead>
                                        <tr>
                                            <th @click="sortList('id')">
                                                <h5 class="text-center"> ID
                                                    <span
                                                            id="sid"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                            <th @click="sortList('dog_id')">
                                                <h5 class="text-center"> Dog ID
                                                    <span
                                                            id="sdogid" style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                            <th @click="sortList('vet_id')">
                                                <h5 class="text-center"> Vet ID
                                                    <span
                                                            id="svetid"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                            <th @click="sortList('date')">
                                                <h5 class="text-center"> Date
                                                    <span
                                                            id="sdate"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                            <th @click="sortList('description')">
                                                <h5 class="text-center"> Description
                                                    <span
                                                            id="sdescription"
                                                            style="opacity: 0"> ▲
                                                    </span>
                                                </h5>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr v-for="appointment in sortedAppointments" v-bind:key="appointment.id"
                                            @click="showModalClickedAppointments(appointment, false, true, false, false)">
                                            <td> {{ appointment.id }}</td>
                                            <td> {{ appointment.dog_id }}</td>
                                            <td> {{ appointment.vet_id }}</td>
                                            <td> {{ appointment.date }}</td>
                                            <td> {{ appointment.description }}</td>
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
        </TabsWrapper>

        <Modal ref="modal" v-show="showModalDogs" @close-modal="updateAndCloseModalDogs()" :div-height="this.divHeight"
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
                    <div class="form-control">
                        <label>Owner ID: </label>
                        <input type="text" v-model="clickedDog.owner_id" id="owner_id"
                               placeholder="Enter dog owner ID"/>
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
                    <div class="form-control">
                        <label>Owner ID: </label>
                        <input type="text" v-model="createFormDog.owner_id" id="owner_id"
                               placeholder="Enter dog owner ID"/>
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

        <Modal ref="modal" v-show="showModalOwners" @close-modal="updateAndCloseModalOwners()"
               :div-height="this.divHeight"
               @keydown.esc="updateAndCloseModalOwners()" tabindex="0" id="modal">
            <div v-show="this.updateOwnerClicked" class="add-form">
                <h6>OWNER DETAILS</h6>
                <form v-on:submit.prevent="onSubmitUpdateOwner" class="add-form">
                    <div class="form-control">
                        <label>ID: </label>
                        <span v-show="this.clickedOwner.id !== ''" id="update_span_id">{{ this.clickedOwner.id }}</span>
                        <input type="text" v-show="this.clickedOwner.id === ''" id="update_input_id"
                               v-bind="this.clickedOwner.id"
                               placeholder="Enter owner ID"/>
                    </div>
                    <div class="form-control">
                        <label>First name: </label>
                        <input type="text" v-model="clickedOwner.first_name" id="first_name"
                               placeholder="Enter owner first name"/>
                    </div>
                    <div class="form-control">
                        <label>Last name: </label>
                        <input type="text" v-model="clickedOwner.last_name" id="last_name"
                               placeholder="Enter owner last name"/>
                    </div>
                    <div class="form-control">
                        <label>Job: </label>
                        <input type="text" v-model="clickedOwner.job" id="job"
                               placeholder="Enter owner job"/>
                    </div>
                    <hr>
                    <input type="submit" value="Update owner" class="btn btn-primary"/>
                </form>
            </div>
            <div v-show="this.createOwnerClicked" class="add-form">
                <h6>OWNER DETAILS</h6>
                <form v-on:submit.prevent="onSubmitCreateOwner" class="add-form">
                    <div class="form-control">
                        <label>First name: </label>
                        <input type="text" v-model="createFormOwner.first_name" id="first_name"
                               placeholder="Enter owner first name"/>
                    </div>
                    <div class="form-control">
                        <label>Last name: </label>
                        <input type="text" v-model="createFormOwner.last_name" id="last_name"
                               placeholder="Enter owner last name"/>
                    </div>
                    <div class="form-control">
                        <label>Job: </label>
                        <input type="text" v-model="createFormOwner.job" id="job"
                               placeholder="Enter owner job"/>
                    </div>
                    <hr>
                    <input type="submit" value="Add owner" class="btn btn-primary"/>
                </form>
            </div>
            <div v-show="this.deleteOwnerClicked" class="add-form">
                <form v-on:submit.prevent="onSubmitDeleteOwner" class="add-form">
                    <div class="form-control">
                        <label>ID: </label>
                        <input type="text" v-model="deleteFormOwner.id" id="id"
                               placeholder="Enter owner ID"/>
                    </div>
                    <hr>
                    <input type="submit" value="Delete owner" class="btn btn-primary"/>
                </form>
            </div>
        </Modal>

        <Modal ref="modal" v-show="showModalVets" @close-modal="updateAndCloseModalVets()" :div-height="this.divHeight"
               @keydown.esc="updateAndCloseModalVets()" tabindex="0" id="modal">
            <div v-show="this.updateVetClicked" class="add-form">
                <h6>VET DETAILS</h6>
                <form v-on:submit.prevent="onSubmitUpdateVet" class="add-form">
                    <div class="form-control">
                        <label>ID: </label>
                        <span v-show="this.clickedVet.id !== ''" id="update_span_id">{{ this.clickedVet.id }}</span>
                        <input type="text" v-show="this.clickedVet.id === ''" id="update_input_id"
                               v-bind="this.clickedVet.id"
                               placeholder="Enter vet ID"/>
                    </div>
                    <div class="form-control">
                        <label>First name: </label>
                        <input type="text" v-model="clickedVet.first_name" id="first_name"
                               placeholder="Enter vet first name"/>
                    </div>
                    <div class="form-control">
                        <label>Last name: </label>
                        <input type="text" v-model="clickedVet.last_name" id="last_name"
                               placeholder="Enter vet last name"/>
                    </div>
                    <div class="form-control">
                        <label>Age: </label>
                        <input type="text" v-model="clickedVet.age" id="age"
                               placeholder="Enter vet age (>25)"/>
                    </div>
                    <div class="form-control">
                        <label>Specialty: </label>
                        <input type="text" v-model="clickedVet.specialty" id="specialty"
                               placeholder="Enter vet specialty"/>
                    </div>
                    <hr>
                    <input type="submit" value="Update vet" class="btn btn-primary"/>
                </form>
            </div>
            <div v-show="this.createVetClicked" class="add-form">
                <h6>VET DETAILS</h6>
                <form v-on:submit.prevent="onSubmitCreateVet" class="add-form">
                    <div class="form-control">
                        <label>First name: </label>
                        <input type="text" v-model="createFormVet.first_name" id="first_name"
                               placeholder="Enter vet first name"/>
                    </div>
                    <div class="form-control">
                        <label>Last name: </label>
                        <input type="text" v-model="createFormVet.last_name" id="last_name"
                               placeholder="Enter vet last name"/>
                    </div>
                    <div class="form-control">
                        <label>Age: </label>
                        <input type="text" v-model="createFormVet.age" id="age"
                               placeholder="Enter vet age"/>
                    </div>
                    <div class="form-control">
                        <label>Specialty: </label>
                        <input type="text" v-model="createFormVet.specialty" id="specialty"
                               placeholder="Enter vet specialty"/>
                    </div>
                    <hr>
                    <input type="submit" value="Add vet" class="btn btn-primary"/>
                </form>
            </div>
            <div v-show="this.deleteVetClicked" class="add-form">
                <form v-on:submit.prevent="onSubmitDeleteVet" class="add-form">
                    <div class="form-control">
                        <label>ID: </label>
                        <input type="text" v-model="deleteFormVet.id" id="id"
                               placeholder="Enter vet ID"/>
                    </div>
                    <hr>
                    <input type="submit" value="Delete vet" class="btn btn-primary"/>
                </form>
            </div>
        </Modal>

        <Modal ref="modal" v-show="showModalLicenses" @close-modal="updateAndCloseModalLicenses()"
               :div-height="this.divHeight"
               @keydown.esc="updateAndCloseModalLicenses()" tabindex="0" id="modal">
            <div v-show="this.updateLicenseClicked" class="add-form">
                <h6>LICENSE DETAILS</h6>
                <form v-on:submit.prevent="onSubmitUpdateLicense" class="add-form">
                    <div class="form-control">
                        <label>ID: </label>
                        <span v-show="this.clickedLicense.id !== ''" id="update_span_id">{{
                            this.clickedLicense.id
                            }}</span>
                        <input type="text" v-show="this.clickedLicense.id === ''" id="update_input_id"
                               v-bind="this.clickedLicense.id"
                               placeholder="Enter license ID"/>
                    </div>
                    <div class="form-control">
                        <label>Serial number: </label>
                        <input type="text" v-model="clickedLicense.serial_number" id="serial_number"
                               placeholder="Enter license serial number"/>
                    </div>
                    <div class="form-control">
                        <label>University: </label>
                        <input type="text" v-model="clickedLicense.university" id="university"
                               placeholder="Enter license university"/>
                    </div>
                    <div class="form-control">
                        <label>Vet ID: </label>
                        <input type="text" v-model="clickedLicense.vet_id" id="vet_id"
                               placeholder="Enter license vet ID"/>
                    </div>
                    <div class="form-control">
                        <label>City: </label>
                        <input type="text" v-model="clickedLicense.city" id="city"
                               placeholder="Enter license city"/>
                    </div>
                    <div class="form-control">
                        <label>Country: </label>
                        <input type="text" v-model="clickedLicense.country" id="country"
                               placeholder="Enter license country"/>
                    </div>
                    <hr>
                    <input type="submit" value="Update license" class="btn btn-primary"/>
                </form>
            </div>
            <div v-show="this.createLicenseClicked" class="add-form">
                <h6>MEDICAL LICENSE DETAILS</h6>
                <form v-on:submit.prevent="onSubmitCreateLicense" class="add-form">
                    <div class="form-control">
                        <label>Serial number: </label>
                        <input type="text" v-model="createFormLicense.serial_number" id="serial_number"
                               placeholder="Enter license serial number"/>
                    </div>
                    <div class="form-control">
                        <label>University: </label>
                        <input type="text" v-model="createFormLicense.university" id="university"
                               placeholder="Enter license university"/>
                    </div>
                    <div class="form-control">
                        <label>City: </label>
                        <input type="text" v-model="createFormLicense.city" id="city"
                               placeholder="Enter license city"/>
                    </div>
                    <div class="form-control">
                        <label>Country: </label>
                        <input type="text" v-model="createFormLicense.country" id="country"
                               placeholder="Enter license country"/>
                    </div>
                    <div class="form-control">
                        <label>Vet ID: </label>
                        <input type="text" v-model="createFormLicense.vet_id" id="vet_id"
                               placeholder="Enter license vet_id"/>
                    </div>
                    <hr>
                    <input type="submit" value="Add medical license" class="btn btn-primary"/>
                </form>
            </div>
            <div v-show="this.deleteLicenseClicked" class="add-form">
                <form v-on:submit.prevent="onSubmitDeleteLicense" class="add-form">
                    <div class="form-control">
                        <label>ID: </label>
                        <input type="text" v-model="deleteFormLicense.id" id="id"
                               placeholder="Enter license ID"/>
                    </div>
                    <hr>
                    <input type="submit" value="Delete license" class="btn btn-primary"/>
                </form>
            </div>
        </Modal>

        <Modal ref="modal" v-show="showModalAppointments" @close-modal="updateAndCloseModalAppointments()"
               :div-height="this.divHeight"
               @keydown.esc="updateAndCloseModalAppointments()" tabindex="0" id="modal">
            <div v-show="this.updateAppointmentClicked" class="add-form">
                <h6>APPOINTMENT DETAILS</h6>
                <form v-on:submit.prevent="onSubmitUpdateAppointment" class="add-form">
                    <div class="form-control">
                        <label>ID: </label>
                        <span v-show="this.clickedAppointment.id !== ''"
                              id="update_span_id">{{ this.clickedAppointment.id }}</span>
                        <input type="text" v-show="this.clickedAppointment.id === ''" id="update_input_id"
                               v-bind="this.clickedAppointment.id"
                               placeholder="Enter appointment ID"/>
                    </div>
                    <div class="form-control">
                        <label>Dog ID: </label>
                        <input type="text" v-model="clickedAppointment.dog_id" id="dog_id"
                               placeholder="Enter appointment dog id"/>
                    </div>
                    <div class="form-control">
                        <label>Vet ID: </label>
                        <input type="text" v-model="clickedAppointment.vet_id" id="vet_id"
                               placeholder="Enter appointment vet id"/>
                    </div>
                    <div class="form-control">
                        <label>Date: </label>
                        <input type="text" v-model="clickedAppointment.date" id="date"
                               placeholder="Enter appointment date"/>
                    </div>
                    <div class="form-control">
                        <label>Price: </label>
                        <input type="text" v-model="clickedAppointment.price" id="price"
                               placeholder="Enter appointment price"/>
                    </div>
                    <div class="form-control">
                        <label>Description: </label>
                        <input type="text" v-model="clickedAppointment.description" id="description"
                               placeholder="Enter appointment description"/>
                    </div>
                    <hr>
                    <input type="submit" value="Update appointment" class="btn btn-primary"/>
                </form>
            </div>
            <div v-show="this.createAppointmentClicked" class="add-form">
                <h6>APPOINTMENT DETAILS</h6>
                <form v-on:submit.prevent="onSubmitCreateAppointment" class="add-form">
                    <div class="form-control">
                        <label>Dog ID: </label>
                        <input type="text" v-model="createFormAppointment.dog_id" id="dog_id"
                               placeholder="Enter appointment dog ID"/>
                    </div>
                    <div class="form-control">
                        <label>Vet ID: </label>
                        <input type="text" v-model="createFormAppointment.vet_id" id="vet_id"
                               placeholder="Enter appointment vet ID"/>
                    </div>
                    <div class="form-control">
                        <label>Date: </label>
                        <input type="text" v-model="createFormAppointment.date" id="date"
                               placeholder="Enter appointment date"/>
                    </div>
                    <div class="form-control">
                        <label>Price: </label>
                        <input type="text" v-model="createFormAppointment.price" id="price"
                               placeholder="Enter appointment price"/>
                    </div>
                    <div class="form-control">
                        <label>Description: </label>
                        <input type="text" v-model="createFormAppointment.description" id="description"
                               placeholder="Enter appointment description"/>
                    </div>
                    <hr>
                    <input type="submit" value="Add appointment" class="btn btn-primary"/>
                </form>
            </div>
            <div v-show="this.deleteAppointmentClicked" class="add-form">
                <form v-on:submit.prevent="onSubmitDeleteAppointment" class="add-form">
                    <div class="form-control">
                        <label>ID: </label>
                        <input type="text" v-model="deleteFormAppointment.id" id="id"
                               placeholder="Enter appointment ID"/>
                    </div>
                    <hr>
                    <input type="submit" value="Delete appointment" class="btn btn-primary"/>
                </form>
            </div>
        </Modal>

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
import VetService from "@/services/VetService";
import LicenseService from "@/services/LicenseService";
import AppointmentService from "@/services/AppointmentService";

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
            // modals
            divHeight: 440,
            showModalDogs: false,
            showModalOwners: false,
            showModalVets: false,
            showModalLicenses: false,
            showModalAppointments: false,


            // dogs
            listDogsClicked: false,
            updateDogClicked: false,
            createDogClicked: false,
            deleteDogClicked: false,

            emptyDog: {
                id: '',
                name: '',
                breed: '',
                age: '',
                weight: '',
                owner_id: ''
            },
            clickedDog: {
                id: '',
                name: '',
                breed: '',
                age: '',
                weight: '',
                owner_id: '',
                number_of_vets: ''
            },
            dogs: [],
            createFormDog: {
                name: '',
                breed: '',
                age: '',
                weight: '',
                owner_id: ''
            },
            deleteFormDog: {
                id: ''
            },
            updateFormDog: {
                id: '',
                name: '',
                breed: '',
                age: '',
                weight: '',
                owner_id: ''
            },
            filterFormDog: {
                weight: ''
            },


            // owners
            listOwnersClicked: false,
            updateOwnerClicked: false,
            createOwnerClicked: false,
            deleteOwnerClicked: false,

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
                job: '',
                number_of_dogs: ''
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


            // vets
            listVetsClicked: false,
            updateVetClicked: false,
            createVetClicked: false,
            deleteVetClicked: false,

            emptyVet: {
                id: '',
                first_name: '',
                last_name: '',
                age: '',
                specialty: ''
            },
            clickedVet: {
                id: '',
                first_name: '',
                last_name: '',
                age: '',
                specialty: '',
                number_of_dogs: ''
            },
            vets: [],
            createFormVet: {
                id: '',
                first_name: '',
                last_name: '',
                age: '',
                specialty: ''
            },
            deleteFormVet: {
                id: '',
                first_name: '',
                last_name: '',
                specialty: ''
            },
            updateFormVet: {
                id: '',
                first_name: '',
                last_name: '',
                age: '',
                specialty: ''
            },


            // medical licenses
            listLicensesClicked: false,
            updateLicenseClicked: false,
            createLicenseClicked: false,
            deleteLicenseClicked: false,

            emptyLicense: {
                id: '',
                serial_number: '',
                university: '',
                city: '',
                country: '',
                vet_id: ''
            },
            clickedLicense: {
                id: '',
                serial_number: '',
                university: '',
                city: '',
                country: '',
                vet_id: ''
            },
            licenses: [],
            createFormLicense: {
                id: '',
                serial_number: '',
                university: '',
                city: '',
                country: '',
                vet_id: ''
            },
            deleteFormLicense: {
                id: '',
                serial_number: '',
                university: '',
                city: '',
                country: '',
                vet_id: ''
            },
            updateFormLicense: {
                id: '',
                serial_number: '',
                university: '',
                city: '',
                country: '',
                vet_id: ''
            },


            // appointments
            listAppointmentsClicked: false,
            updateAppointmentClicked: false,
            createAppointmentClicked: false,
            deleteAppointmentClicked: false,

            emptyAppointment: {
                id: '',
                dog_id: '',
                vet_id: '',
                date: '',
                price: '',
                description: ''
            },
            clickedAppointment: {
                id: '',
                dog_id: '',
                vet_id: '',
                date: '',
                price: '',
                description: ''
            },
            appointments: [],
            createFormAppointment: {
                id: '',
                dog_id: '',
                vet_id: '',
                date: '',
                price: '',
                description: ''
            },
            deleteFormAppointment: {
                id: '',
                dog_id: '',
                vet_id: '',
                date: '',
                price: '',
                description: ''
            },
            updateFormAppointment: {
                id: '',
                dog_id: '',
                vet_id: '',
                date: '',
                price: '',
                description: ''
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
                if (a[this.currentSort] > b[this.currentSort]) return modifier;
                return 0;
            });
        },

        sortedOwners: function () {
            return this.owners.sort((a, b) => {
                let modifier = 1;
                if (this.currentSortDir === 'desc') modifier = -1;
                if (a[this.currentSort] < b[this.currentSort]) return -1 * modifier;
                if (a[this.currentSort] > b[this.currentSort]) return modifier;
                return 0;
            });
        },

        sortedVets: function () {
            return this.vets.sort((a, b) => {
                let modifier = 1;
                if (this.currentSortDir === 'desc') modifier = -1;
                if (a[this.currentSort] < b[this.currentSort]) return -1 * modifier;
                if (a[this.currentSort] > b[this.currentSort]) return modifier;
                return 0;
            });
        },

        sortedLicenses: function () {
            return this.licenses.sort((a, b) => {
                let modifier = 1;
                if (this.currentSortDir === 'desc') modifier = -1;
                if (a[this.currentSort] < b[this.currentSort]) return -1 * modifier;
                if (a[this.currentSort] > b[this.currentSort]) return modifier;
                return 0;
            });
        },

        sortedAppointments: function () {
            return this.appointments.sort((a, b) => {
                let modifier = 1;
                if (this.currentSortDir === 'desc') modifier = -1;
                if (a[this.currentSort] < b[this.currentSort]) return -1 * modifier;
                if (a[this.currentSort] > b[this.currentSort]) return modifier;
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
            this.showModalDogs = true;
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
            this.showModalOwners = true;
            if (this.listOwnersClicked) {
                this.divHeight = 150;
            } else if (this.deleteOwnerClicked) {
                this.divHeight = 200;
            } else {
                this.divHeight = 440;
            }
        },

        showModalClickedVets(vet, lClicked, uClicked, cClicked, dClicked) {
            this.clickedVet = vet;
            this.listVetsClicked = lClicked;
            this.updateVetClicked = uClicked;
            this.createVetClicked = cClicked;
            this.deleteVetClicked = dClicked;
            this.showModalVets = true;
            if (this.listVetsClicked) {
                this.divHeight = 150;
            } else if (this.deleteVetClicked) {
                this.divHeight = 200;
            } else {
                this.divHeight = 440;
            }
        },

        showModalClickedLicenses(license, lClicked, uClicked, cClicked, dClicked) {
            this.clickedLicense = license;
            this.listLicensesClicked = lClicked;
            this.updateLicenseClicked = uClicked;
            this.createLicenseClicked = cClicked;
            this.deleteLicenseClicked = dClicked;
            this.showModalLicenses = true;
            if (this.listLicensesClicked) {
                this.divHeight = 150;
            } else if (this.deleteLicenseClicked) {
                this.divHeight = 200;
            } else {
                this.divHeight = 440;
            }
        },

        showModalClickedAppointments(appointment, lClicked, uClicked, cClicked, dClicked) {
            this.clickedAppointment = appointment;
            this.listAppointmentsClicked = lClicked;
            this.updateAppointmentClicked = uClicked;
            this.createAppointmentClicked = cClicked;
            this.deleteAppointmentClicked = dClicked;
            this.showModalAppointments = true;
            if (this.listAppointmentsClicked) {
                this.divHeight = 150;
            } else if (this.deleteAppointmentClicked) {
                this.divHeight = 200;
            } else {
                this.divHeight = 440;
            }
        },

        updateAndCloseModalDogs() {
            this.showModalDogs = false
            document.getElementById('update_input_id').value = '';
            this.clickedDog = this.emptyDog;
        },

        updateAndCloseModalOwners() {
            this.showModalOwners = false
            document.getElementById('update_input_id').value = '';
            this.clickedOwner = this.emptyOwner;
        },

        updateAndCloseModalVets() {
            this.showModalVets = false
            document.getElementById('update_input_id').value = '';
            this.clickedVet = this.emptyVet;
        },

        updateAndCloseModalLicenses() {
            this.showModalLicenses = false
            document.getElementById('update_input_id').value = '';
            this.clickedLicense = this.emptyLicense;
        },

        updateAndCloseModalAppointments() {
            this.showModalAppointments = false
            document.getElementById('update_input_id').value = '';
            this.clickedAppointment = this.emptyAppointment;
        },

        onSubmitCreateDog(e) {
            e.preventDefault()
            if (!this.createFormDog.name) {
                alert('Please add a NAME for the dog')
                return
            }

            if (!this.createFormDog.breed) {
                alert('Please add a BREED for the dog')
                return
            }

            if (!this.createFormDog.age) {
                alert('Please add an AGE for the dog')
                return
            }

            if (!this.createFormDog.weight) {
                alert('Please add a WEIGHT for the dog')
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
            this.createFormDog.owner_id = ''
        },

        onSubmitCreateOwner(e) {
            e.preventDefault()
            if (!this.createFormOwner.first_name) {
                alert('Please add a FIRST NAME for the owner')
                return
            }

            if (!this.createFormOwner.last_name) {
                alert('Please add a LAST NAME for the owner')
                return
            }

            if (!this.createFormOwner.job) {
                alert('Please give the owner a job')
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

        onSubmitCreateVet(e) {
            e.preventDefault()
            if (!this.createFormVet.first_name) {
                alert('Please add a FIRST NAME to the vet')
                return
            }

            if (!this.createFormVet.last_name) {
                alert('Please add a LAST NAME to the vet')
                return
            }

            if (!this.createFormVet.age) {
                alert('Please add an AGE to the vet')
                return
            }

            if (!this.createFormVet.specialty) {
                alert('Please add a SPECIALTY to the vet')
                return
            }

            this.showLoader = true
            axios.post(VetService.getUrl(), this.createFormVet)
                .then((res) => {
                    window.location.reload()
                })
                .catch((error) => {
                    alert(JSON.stringify(error.response.data));
                    console.log(JSON.stringify(error.response.data));
                }).finally(() => {
                this.showLoader = false
            });
            this.createFormVet.first_name = ' '
            this.createFormVet.last_name = ' '
            this.createFormVet.age = ''
            this.createFormVet.specialty = ' '
        },

        onSubmitCreateLicense(e) {
            e.preventDefault()
            if (!this.createFormLicense.serial_number) {
                alert('Please add a SERIAL NUMBER for the medical license')
                return
            }

            if (!this.createFormLicense.university) {
                alert('Please add a UNIVERSITY for the medical license')
                return
            }

            if (!this.createFormLicense.city) {
                alert('Please add the CITY of the medical license')
                return
            }

            if (!this.createFormLicense.country) {
                alert('Please add the COUNTRY of the medical license')
                return
            }

            if (!this.createFormLicense.vet_id) {
                alert('Please add a VET ID')
                return
            }

            this.showLoader = true
            axios.post(LicenseService.getUrl(), this.createFormLicense)
                .then((res) => {
                    window.location.reload()
                })
                .catch((error) => {
                    alert(JSON.stringify(error.response.data));
                    console.log(JSON.stringify(error.response.data));
                }).finally(() => {
                this.showLoader = false
            });
            this.createFormLicense.serial_number = ' '
            this.createFormLicense.university = ' '
            this.createFormLicense.city = ' '
            this.createFormLicense.country = ' '
            this.createFormLicense.vet_id = ' '
        },

        onSubmitCreateAppointment(e) {
            e.preventDefault()
            if (!this.createFormAppointment.dog_id) {
                alert('Please add a DOG ID for the appointment')
                return
            }

            if (!this.createFormAppointment.vet_id) {
                alert('Please add a VET ID for the appointment')
                return
            }

            if (!this.createFormAppointment.date) {
                alert('Please add a DATE for the appointment')
                return
            }

            if (!this.createFormAppointment.price) {
                alert('Please add an appointment PRICE')
                return
            }

            if (!this.createFormAppointment.description) {
                alert('Please add an appointment DESCRIPTION')
                return
            }

            this.showLoader = true
            axios.post(AppointmentService.getUrl(), this.createFormAppointment)
                .then((res) => {
                    window.location.reload()
                })
                .catch((error) => {
                    alert(JSON.stringify(error.response.data));
                    console.log(JSON.stringify(error.response.data));
                }).finally(() => {
                this.showLoader = false
            });
            this.createFormAppointment.dog_id = ' '
            this.createFormAppointment.vet_id = ' '
            this.createFormAppointment.date = ' '
            this.createFormAppointment.price = ' '
            this.createFormAppointment.description = ' '
        },

        onSubmitDeleteDog(e) {
            e.preventDefault()
            if (!this.deleteFormDog.id) {
                alert('Please add an ID')
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
                alert('Please add an ID')
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

        onSubmitDeleteVet(e) {
            e.preventDefault()
            if (!this.deleteFormVet.id) {
                alert('Please add an ID')
                return
            }

            this.showLoader = true
            axios.delete(VetService.getUrl() + '/' + this.deleteFormVet.id, this.deleteFormVet)
                .then((res) => {
                    window.location.reload()
                })
                .catch((error) => {
                    alert(JSON.stringify(error.response.data));
                    console.log(JSON.stringify(error.response.data));
                }).finally(() => {
                this.showLoader = false
            });
            this.deleteFormVet.id = ' '
        },

        onSubmitDeleteLicense(e) {
            e.preventDefault()
            if (!this.deleteFormLicense.id) {
                alert('Please add an ID')
                return
            }

            this.showLoader = true
            axios.delete(LicenseService.getUrl() + '/' + this.deleteFormLicense.id, this.deleteFormLicense)
                .then((res) => {
                    window.location.reload()
                })
                .catch((error) => {
                    alert(JSON.stringify(error.response.data));
                    console.log(JSON.stringify(error.response.data));
                }).finally(() => {
                this.showLoader = false
            });
            this.deleteFormLicense.id = ' '
        },

        onSubmitDeleteAppointment(e) {
            e.preventDefault()
            if (!this.deleteFormAppointment.id) {
                alert('Please add an ID')
                return
            }

            this.showLoader = true
            axios.delete(AppointmentService.getUrl() + '/' + this.deleteFormAppointment.id, this.deleteFormAppointment)
                .then((res) => {
                    window.location.reload()
                })
                .catch((error) => {
                    alert(JSON.stringify(error.response.data));
                    console.log(JSON.stringify(error.response.data));
                }).finally(() => {
                this.showLoader = false
            });
            this.deleteFormAppointment.id = ' '
        },

        onSubmitUpdateDog(e) {
            e.preventDefault()

            const localDog = {
                id: '',
                name: '',
                breed: '',
                age: '',
                weight: '',
                owner_id: ''
            }
            try {
                localDog.id = this.clickedDog.id;
                localDog.name = this.clickedDog.name;
                localDog.breed = this.clickedDog.breed;
                localDog.age = this.clickedDog.age;
                localDog.weight = this.clickedDog.weight;
                localDog.owner_id = this.clickedDog.owner_id;

                if (!localDog.id) {
                    localDog.id = document.getElementById('update_input_id').value;
                }

                if (!localDog.id) {
                    localDog.id = document.getElementById('update_span_id').textContent;
                }

                if (!localDog.id) {
                    alert('Please add an ID');

                    this.clickedDog = this.emptyDog;

                    window.location.reload();

                    return;
                }
                if (!localDog.name) {
                    alert('Please add a name');
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
            } finally {
                this.clickedDog = this.emptyDog;
                this.showLoader = false;
            }
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
                alert('Please add an ID');

                this.clickedOwner = this.emptyOwner;

                window.location.reload();

                return;
            }
            if (!localOwner.first_name) {
                alert('Please add a FIRST NAME');
                this.clickedOwner = this.emptyOwner;

                window.location.reload();

                return;
            }

            if (!localOwner.last_name) {
                alert('Please add a LAST NAME');
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

        onSubmitUpdateVet(e) {
            e.preventDefault()

            // use local const, we don't want changes to affect for due to reactivity
            const localVet = {
                id: '',
                first_name: '',
                last_name: '',
                age: '',
                specialty: ''
            }

            localVet.id = this.clickedVet.id;
            localVet.first_name = this.clickedVet.first_name;
            localVet.last_name = this.clickedVet.last_name;
            localVet.age = this.clickedVet.age;
            localVet.specialty = this.clickedVet.specialty;

            if (!localVet.id) {
                localVet.id = document.getElementById('update_input_id').value;
            }

            if (!localVet.id) {
                localVet.id = document.getElementById('update_span_id').textContent;
            }

            if (!localVet.id) {
                alert('Please add an ID');

                this.clickedVet = this.emptyVet;

                window.location.reload();

                return;
            }
            if (!localVet.first_name) {
                alert('Please add a FIRST NAME');
                this.clickedVet = this.emptyVet;

                window.location.reload();

                return;
            }

            if (!localVet.last_name) {
                alert('Please add a LAST NAME');
                this.clickedVet = this.emptyVet;

                window.location.reload();

                return;
            }

            this.showLoader = true;
            axios.put(VetService.getUrl() + '/' + localVet.id, localVet)
                .then((res) => {
                    window.location.reload();
                })
                .catch((error) => {
                    alert(JSON.stringify(error.response.data));
                    console.log(JSON.stringify(error.response.data));
                    window.location.reload();
                }).finally(() => {
                this.clickedVet = this.emptyVet;
                this.showLoader = false;
            });
        },

        onSubmitUpdateLicense(e) {
            e.preventDefault()

            // use local const, we don't want changes to affect for due to reactivity
            const localLicense = {
                id: '',
                serial_number: '',
                university: '',
                city: '',
                country: '',
                vet_id: ''
            }

            localLicense.id = this.clickedLicense.id;
            localLicense.serial_number = this.clickedLicense.serial_number;
            localLicense.university = this.clickedLicense.university;
            localLicense.city = this.clickedLicense.city;
            localLicense.country = this.clickedLicense.country;
            localLicense.vet_id = this.clickedLicense.vet_id;

            if (!localLicense.id) {
                localLicense.id = document.getElementById('update_input_id').value;
            }

            if (!localLicense.id) {
                localLicense.id = document.getElementById('update_span_id').textContent;
            }

            if (!localLicense.id) {
                alert('Please add an ID');

                this.clickedLicense = this.emptyLicense;

                window.location.reload();

                return;
            }
            if (!localLicense.serial_number) {
                alert('Please add a SERIAL NUMBER');
                this.clickedLicense = this.emptyLicense;

                window.location.reload();

                return;
            }

            if (!localLicense.university) {
                alert('Please add a UNIVERSITY');
                this.clickedLicense = this.emptyLicense;

                window.location.reload();

                return;
            }

            if (!localLicense.city) {
                alert('Please add a CITY');
                this.clickedLicense = this.emptyLicense;

                window.location.reload();

                return;
            }

            if (!localLicense.country) {
                alert('Please add a COUNTRY');
                this.clickedLicense = this.emptyLicense;

                window.location.reload();

                return;
            }

            this.showLoader = true;
            axios.put(LicenseService.getUrl() + '/' + localLicense.id, localLicense)
                .then((res) => {
                    window.location.reload();
                })
                .catch((error) => {
                    alert(JSON.stringify(error.response.data));
                    console.log(JSON.stringify(error.response.data));
                    window.location.reload();
                }).finally(() => {
                this.clickedLicense = this.emptyLicense;
                this.showLoader = false;
            });
        },

        onSubmitUpdateAppointment(e) {
            e.preventDefault()

            // use local const, we don't want changes to affect for due to reactivity
            const localAppointment = {
                id: '',
                dog_id: '',
                vet_id: '',
                date: '',
                price: '',
                description: ''
            }

            localAppointment.id = this.clickedAppointment.id;
            localAppointment.dog_id = this.clickedAppointment.dog_id;
            localAppointment.vet_id = this.clickedAppointment.vet_id;
            localAppointment.price = this.clickedAppointment.price;
            localAppointment.date = this.clickedAppointment.date;
            localAppointment.description = this.clickedAppointment.description;

            if (!localAppointment.id) {
                localAppointment.id = document.getElementById('update_input_id').value;
            }

            if (!localAppointment.id) {
                localAppointment.id = document.getElementById('update_span_id').textContent;
            }

            if (!localAppointment.id) {
                alert('Please add an APPOINTMENT ID');

                this.clickedAppointment = this.emptyAppointment;

                window.location.reload();

                return;
            }
            if (!localAppointment.dog_id) {
                alert('Please add a DOG ID');
                this.clickedAppointment = this.emptyAppointment;

                window.location.reload();

                return;
            }

            if (!localAppointment.vet_id) {
                alert('Please add a VET ID');
                this.clickedAppointment = this.emptyAppointment;

                window.location.reload();

                return;
            }

            if (!localAppointment.date) {
                alert('Please add a DATE for the appointment');
                this.clickedAppointment = this.emptyAppointment;

                window.location.reload();

                return;
            }

            if (!localAppointment.price) {
                alert('Please add a PRICE for the appointment');
                this.clickedAppointment = this.emptyAppointment;

                window.location.reload();

                return;
            }

            this.showLoader = true;
            axios.put(AppointmentService.getUrl() + '/' + localAppointment.id, localAppointment)
                .then((res) => {
                    window.location.reload();
                })
                .catch((error) => {
                    alert(JSON.stringify(error.response.data));
                    console.log(JSON.stringify(error.response.data));
                    window.location.reload();
                }).finally(() => {
                this.clickedAppointment = this.emptyAppointment;
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

        loadPageOwners() {
            this.showLoader = true
            const updatedPage = this.page - 1;
            this.showLoader = true;
            axios.get(OwnerService.getUrl() + '?page=' + updatedPage + '&size=' + this.recordsPerPage)
                .then((response) => {
                    this.showLoader = false
                    this.owners = response.data.owners;
                    this.totalRecords = response.data.totalItems;
                    this.totalPages = response.data.totalPages;
                    this.page = response.data.currentPage + 1;
                }).finally(() => {
                this.showLoader = false;
            });
        },

        loadPageVets() {
            this.showLoader = true
            const updatedPage = this.page - 1;
            this.showLoader = true;
            axios.get(VetService.getUrl() + '?page=' + updatedPage + '&size=' + this.recordsPerPage)
                .then((response) => {
                    this.showLoader = false
                    this.vets = response.data.vets;
                    this.totalRecords = response.data.totalItems;
                    this.totalPages = response.data.totalPages;
                    this.page = response.data.currentPage + 1;
                }).finally(() => {
                this.showLoader = false;
            });
        },

        loadPageLicenses() {
            this.showLoader = true
            const updatedPage = this.page - 1;
            this.showLoader = true;
            axios.get(LicenseService.getUrl() + '?page=' + updatedPage + '&size=' + this.recordsPerPage)
                .then((response) => {
                    this.showLoader = false
                    this.licenses = response.data.licenses;
                    this.totalRecords = response.data.totalItems;
                    this.totalPages = response.data.totalPages;
                    this.page = response.data.currentPage + 1;
                }).finally(() => {
                this.showLoader = false;
            });
        },

        loadPageAppointments() {
            this.showLoader = true
            const updatedPage = this.page - 1;
            this.showLoader = true;
            axios.get(AppointmentService.getUrl() + '?page=' + updatedPage + '&size=' + this.recordsPerPage)
                .then((response) => {
                    this.showLoader = false
                    this.appointments = response.data.appointments;
                    this.totalRecords = response.data.totalItems;
                    this.totalPages = response.data.totalPages;
                    this.page = response.data.currentPage + 1;
                }).finally(() => {
                this.showLoader = false;
            });
        },

        onPageChangeDogs(page) {
            this.page = page
            this.loadPageDogs()
        },

        onPageChangeOwners(page) {
            this.page = page
            this.loadPageOwners()
        },

        onPageChangeVets(page) {
            this.page = page
            this.loadPageVets()
        },

        onPageChangeLicenses(page) {
            this.page = page
            this.loadPageLicenses()
        },

        onPageChangeAppointments(page) {
            this.page = page
            this.loadPageAppointments()
        },

        onChangeRecordsPerPage() {
            this.loadPageDogs()
            this.loadPageOwners()
            this.loadPageVets()
            this.loadPageLicenses()
            this.loadPageAppointments()
        },

        gotoPageDogs() {
            if (!isNaN(parseInt(this.enterPageNo))) {
                this.page = parseInt(this.enterPageNo)
                this.loadPageDogs()
            }
        },

        gotoPageOwners() {
            if (!isNaN(parseInt(this.enterPageNo))) {
                this.page = parseInt(this.enterPageNo)
                this.loadPageOwners()
            }
        },

        gotoPageVets() {
            if (!isNaN(parseInt(this.enterPageNo))) {
                this.page = parseInt(this.enterPageNo)
                this.loadPageVets()
            }
        },

        gotoPageLicenses() {
            if (!isNaN(parseInt(this.enterPageNo))) {
                this.page = parseInt(this.enterPageNo)
                this.loadPageLicenses()
            }
        },

        gotoPageAppointments() {
            if (!isNaN(parseInt(this.enterPageNo))) {
                this.page = parseInt(this.enterPageNo)
                this.loadPageAppointments()
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

    },

    created() {
        this.loadPageDogs();
        this.loadPageOwners();
        this.loadPageVets();
        this.loadPageLicenses();
        this.loadPageAppointments();

        this.escapeHandler = (e) => {
            if (e.key === 'Escape' && this.showModalDogs) {
                this.updateAndCloseModalDogs();
            }
            if (e.key === 'Escape' && this.showModalOwners) {
                this.updateAndCloseModalOwners();
            }
            if (e.key === 'Escape' && this.showModalVets) {
                this.updateAndCloseModalVets();
            }
            if (e.key === 'Escape' && this.showModalLicenses) {
                this.updateAndCloseModalLicenses();
            }
            if (e.key === 'Escape' && this.showModalAppointments) {
                this.updateAndCloseModalAppointments();
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

.tabs {
    background-color: rgba(140, 197, 197, 0.82);
}

#dogTable {
    width: 800px;
    table-layout: auto;
}

#ownerTable {
    width: 800px;
    table-layout: auto;
}

#vetTable {
    width: 800px;
    table-layout: auto;
}

#licenseTable {
    width: 800px;
    table-layout: auto;
}

#appointmentTable {
    width: 800px;
    table-layout: auto;
}

#container {
    position: relative;
    background-color: #ffffff;
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