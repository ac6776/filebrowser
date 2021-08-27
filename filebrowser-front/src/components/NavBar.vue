<template>
  <div class="btn-group">
    <button type="button" class="btn btn-outline-secondary" title="Go back">
      <i class="bi bi-arrow-left-circle icon"></i>
    </button>
    <button type="button" class="btn btn-outline-secondary" title="Go forward">
      <i class="bi bi-arrow-right-circle icon"></i>
    </button>
    <button type="button"
            @click="goHome()" class="btn btn-outline-secondary" title="Home directory">
      <i class="bi bi-house icon"></i>
    </button>
<!--    <button v-on:click="showOrHideFiles()" type="button" class="btn btn-outline-secondary" title="Hide/show hidden files">-->
    <button type="button"
            @click="showOrHideFiles()"
            :class="{active: this.active}"
            class="btn btn-outline-secondary" title="Hide/show hidden files">
      <i class="bi bi-eye-slash icon"></i>
    </button>
  </div>
</template>

<script>
import FilesList from "@/components/FilesList";

export default {
  name: 'NavBar',
  data() {
    return {
      active: true,
      history: []
    }
  },
  methods: {
    showOrHideFiles: function () {
      this.active = !this.active,
      this.$emit('showHiddenFromBar')
    },
    goHome: function () {
      // this.$emit('goHome')
      this.$store.commit('goHome')
    }
  }
}
</script>

<style>
  .icon {
    font-size: 2rem;
    line-height: 2rem;
    padding: 5px;
  }
</style>