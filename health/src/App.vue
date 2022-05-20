<template>
  <div id="app">

    <Header></Header>

    <layout></layout>


  </div>

</template>

<script>
import HelloWorld from './components/HelloWorld'
import Side from "./components/side";
import Header from "./components/header";
import Layout from "./views/layout";

export default {
  name: 'App',
  components: {
    Layout,
    Header,
    Side,
    HelloWorld
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
}
body{
  margin:0;
  padding:0;
}
</style>
