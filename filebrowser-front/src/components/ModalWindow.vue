<template>
  <!-- Modal -->
  <div class="modal fade" id="error500Modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          This is a modal
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import bootstrap from 'bootstrap/dist/js/bootstrap.bundle.min'

export default {
  name: "modalWindow",
  props: {
    counter: Number
  },
  data() {
    return {
      // errorModal: document.getElementById('error500Modal')
      myModal: null
    }
  },
  methods: {
    showModal() {
      let myModal = new bootstrap.Modal(document.getElementById('error500Modal'), {
        backdrop: true,
        focus: true
      });
      myModal.show();
    }
  },
  watch: {
    counter(newVal, oldVal) {
      this.showModal()
    }
  }
}
</script>

<style scoped>

</style>