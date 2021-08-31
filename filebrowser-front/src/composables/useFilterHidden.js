import {computed, ref} from "vue";

export function useFilterHidden(showHidden, files) {
    const show = ref(false)
    const showHiddenFiles = computed(() => {
        if(files.value) {
            return files.value.filter(f => {
                if (showHidden.value) {
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
        showHiddenFiles
    }
}