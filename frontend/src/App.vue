<template>
    <div id="app">
        <Dogs />
    </div>
</template>

<script>
import Dogs from './components/Dogs.vue'
import Owners from './components/Dogs.vue'

export default {
    name: 'App',
    components: {
        Dogs,
        Owners
    }
}
</script>

<style>
#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    table-layout: auto;
    color: #000000;
    margin-top: 60px;
}
</style>