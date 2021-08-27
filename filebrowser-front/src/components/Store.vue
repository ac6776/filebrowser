<script>

export default {
  name: "Store",
  state () {
    return {
      home: null,
      count: 0
    }
  },
  mutations: {
    increment (state) {
      state.count++
    },
  }
}
</script>