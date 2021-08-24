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
            @click="showOrHideFiles()" class="btn btn-outline-secondary" title="Hide/show hidden files">
      <i class="bi bi-eye-slash icon"></i>
    </button>
  </div>
</template>

<script>

export default {
  name: 'NavBar',
  data() {
    return {
      history: []
    }
  },
  methods: {
    showOrHideFiles: function () {
      this.$emit('showHiddenFromBar')
    },
    goHome: function () {
      this.$emit('goHome')
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