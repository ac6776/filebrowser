<script>

</script>

import {default as axios} from "axios";

let getError = function(error) {
    console.log(error);
    if (!error.response) {
        return {
            type: 'con',
            msg: 'Error: Network Connection Refused'
        }
    } else if (error.response.status === 500) {
        return {
            type: '500',
            msg: 'Error: Serverside error [500]'
        }
    } else if (error.response.status === 404) {
        return {
            type: '500',
            msg: 'Error: Not found [404]'
        }
    } else {
        return {
            type: 'unknown',
            msg: 'unknown error'
        }
    }
}

export default {
    fetch: function (requestType, url) {
        let dataObject = null;
        let data = {
            home: null,
            parent: null,
            current: null,
            files: null,
            loading: true,
            errored: false
        }
        if (requestType === 'get') {
            dataObject = axios.get('http://localhost:8080/')
        } else {
            dataObject = axios.post('http://localhost:8080/', {
                url
                // path: '2222'
            })
        }
        dataObject
            .then(response => (
                data.home = requestType === 'get' ? response.data.current : null,
                data.parent = response.data.parent,
                data.current = response.data.current,
                data.files = response.data.fileObjectList
            ))
            .catch((error) => {
                data.errored = getError(error)
            })
            .finally(() => {
                data.loading = false;
            });

        return data;
    }
}