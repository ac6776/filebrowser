<template>
  <div class="btn-group">
    <button type="button"
            @click="this.$emit('goBack')"
            class="btn btn-light"
            title="Go back"
            :disabled="undoHistory.length === 0">
      <i class="bi bi-arrow-counterclockwise"></i>
      <span v-if="undoHistory.length > 0" 
        class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
        {{undoHistory.length}}
      <span class="visually-hidden">redo history</span>
      </span>
    </button>
    <button type="button"
            @click="this.$emit('goForward')"
            class="btn btn-light"
            title="Go forward"
            :disabled="redoHistory.length === 0">
      <i class="bi bi-arrow-clockwise"></i>
      <span v-if="redoHistory.length > 0" 
        class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
        {{redoHistory.length}}
      <span class="visually-hidden">redo history</span>
      </span>
    </button>
    <button type="button"
            @click="this.$emit('goHome', this.$store.state.home.path)"
            class="btn btn-light"
            title="Home directory">
      <i class="bi bi-house"></i>
    </button>
    <button type="button"
            @click="showOrHideFiles"
            :class="{active: this.active}"
            class="btn btn-light"
            title="Hide/show hidden files">
      <i v-if="!active" class="bi bi-eye-slash "></i>
      <i v-else class="bi bi-eye-slash-fill"></i>
    </button>
  </div>
</template>

<script>

export default {
  name: 'NavBar',
  data() {
    return {
      active: true,
    }
  },
  props: {
    undoHistory: {
      type: Array,
      required: true
    },
    redoHistory: {
      type: Array,
      required: true
    }
  },
  methods: {
    showOrHideFiles: function () {
      this.active = !this.active,
      this.$emit('showHiddenFromBar')
    }
  }
}
</script>


<style lang="css" scoped>
  button i {
    font-size: 2rem;
    line-height: 2rem;
    padding: 6px;
  }
  button span {
    z-index: 1500;
  }
</style>