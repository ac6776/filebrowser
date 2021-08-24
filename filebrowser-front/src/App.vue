<template>
  <div class="container mt-5">
    <div class="d-flex justify-content-between">
      <h1 class="primary-font">My filebrowser</h1>
      <!--
        Child to parent data transfer using $emit
      -->
      <NavBar @showHiddenFromBar="show($event)"></NavBar>
    </div>
    <!--
      Parent to child data transfer using Props
    -->
    <FilesList :showHidden="this.showHidden"></FilesList>
  </div>
</template>

<script>
import FilesList from "@/components/FilesList";
import NavBar from "@/components/NavBar";

export default {
  name: 'App',
  data() {
    return {
      showHidden: false
    }
  },
  components: {
    FilesList,
    NavBar
  },
  methods: {
    show: function () {
      this.showHidden = !this.showHidden,
      console.log(this.showHidden + " form App.vue")
    }
  }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap');
.primary-font {
  font-family: 'Bebas Neue', cursive;
}
</style>
