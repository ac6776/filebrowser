import {ref, onMounted, watch} from "vue";
import {default as axios} from "axios";
import {getError} from "@/composables/error";
import {useStore} from "vuex";

export function useFetchingFiles() {
    const store = useStore()

    const parent = ref(null)
    const current = ref(null)
    const files = ref([])
    const errored = ref(false)
    const loading = ref(true)

    function setHome(value) {
        if (!store.state.home) {
            store.commit('setHome', value)
        } else {
            console.log('Home is already set');
        }
    }

    const fetching = async (requestType, url) => {
        let response = null;
        try {
            if (requestType === 'get') {
                response = await axios.get('http://localhost:8080/');
            } else {
                response = await axios.post('http://localhost:8080/', {
                    path: url
                })
            }
            parent.value = response.data.parent
            current.value = response.data.current
            files.value = response.data.fileObjectList

            setHome(current.value)
        } catch (e) {
            errored.value = getError(e)
        } finally {
            loading.value = false;
        }
    }

    onMounted(fetching('get'))

    return {
        parent,
        current,
        files,
        errored,
        loading,
        fetching
    }
}