export function getError(error) {
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