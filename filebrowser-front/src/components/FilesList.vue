<template>


  <div class="row align-items-start">
    <section v-if="loading">
      <div class="d-flex align-items-center">
        <strong>Loading...</strong>
        <div class="spinner-border ms-auto" role="status" aria-hidden="true"></div>
      </div>
    </section>
    <section v-if="errored.type !== 'OK'">
      <div class="alert alert-danger mt-2 d-flex align-items-center" role="alert">
        <i v-if="errored.type === 'con'" class="bi bi-wifi-off icon"></i>
        <i v-else class="bi bi-exclamation-triangle-fill icon"></i>
        {{ errored.msg }}
      </div>
    </section>
    <div class="d-flex flex-wrap mt-2">
      <button
          v-if="parent"
          @click="step(parent)"
          type="button" class="btn btn-light m-1"><i class="bi bi-arrow-90deg-up go-back-arrow"></i>
        ..
      </button>
      <File v-for="file in files" :key="file.path"
      :file="file"
      @step="step($event)"/>
    </div>
  </div>
</template>

<script>
import File from '@/components/File'

export default {
  name: 'FilesList',
  props: {
    parent: Object,
    current: Object,
    files: Array,
    loading: Boolean,
    errored: Object
  },
  components: {
    File
  },
  methods: {
    checkForDir(file) {
      return file.directory
    },
    step(node) {
      this.$emit('step', node)
    }
  },
}
</script>
<style>
  .go-back-arrow {
    font-size: .85rem;
    margin-right: 1rem;
  }
</style>