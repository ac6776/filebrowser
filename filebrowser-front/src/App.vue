<template>
  <div class="container mt-5">
    <div class="d-flex justify-content-between">
      <h1 class="primary-font">My filebrowser</h1>
      <!--
        Child to parent data transfer using $emit
      -->
      <NavBar
          @showHiddenFromBar="show($event)"
          @goHome="goHome($event)"/>
    </div>
    <!--
      Parent to child data transfer using Props
    -->
    <FilesList
        :showHidden="this.showHidden"
        :homeRequsted="this.homeRequested"/>
  </div>
</template>

<script>
import FilesList from "@/components/FilesList";
import NavBar from "@/components/NavBar";

export default {
  name: 'App',
  data() {
    return {
      showHidden: false,
      homeRequested: false
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
    },
    goHome: function () {
      this.homeRequested = !this.homeRequested
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
