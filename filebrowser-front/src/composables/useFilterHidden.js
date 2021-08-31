import {computed, ref} from "vue";

export function useFilterHidden(files) {
    const show = ref(false)
    const showHidden = computed(() => {
        if(files.value) {
            return files.value.filter(f => {
                if (show.value) {
                    return f;
                } else {
                    if (!f.hidden) {
                        return f;
                    }
                }
            })
        }
    })

    return {
        show, showHidden
    }
}