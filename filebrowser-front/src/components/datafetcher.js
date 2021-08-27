import {default as axios} from "axios";

let data = {
    home: null,
    parent: null,
    current: null,
    files: null
}

export default {
    fetcher: async function (requestType, url) {
        let response = await this.getResponse(requestType, url);
        console.log(response.response)
            if (!data.errored) {
                data.home = requestType === 'get' ? response.data.current : null,
                data.parent = response.data.parent,
                data.current = response.data.current,
                data.files = response.data.fileObjectList
            }
            return data;
    },
    getResponse: async function(requestType, url) {
        let response;
        if (requestType === 'get') {
            response = await axios.get('http://localhost:8080/')
        } else {
            response = await axios.post('http://localhost:8080/', {
                path: url
                // path: '2222'
            })
        }
        // console.log('From response ' + response.then(res => res.data.status))
        return response;
    },
    getError: function(error) {
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
}