export default {
  name: "Store",
  state () {
    return {
      home: null,
    }
  },
  mutations: {
    setHome(state, home) {
      state.home = home
    }
  }
}