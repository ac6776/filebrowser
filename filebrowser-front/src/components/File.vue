<template>
    <button
          @click="step(file)"
          type="button" class="btn btn-light m-1" 
          :disabled="!file.directory">

        <span v-if="file.directory">
          <i class="bi bi-folder"></i>
        </span>
        <span v-else>
          <i class="bi bi-file-earmark"></i>
        </span>
        {{ file.name }}
      </button>
</template>

<script>
export default ({
    name: 'File',
    props: {
        file: {
            type: Object,
            required: true
        }
    },
    methods: {
        step() {
            this.$emit('step', this.file)
        }
    }
})
</script>
