<script>

    export default {
        name: "API"
    }

    function handleErrors(response) {
        if (!response.ok) {
            return response.text().then(err => { throw err; });
        }
        return response;
    }

</script>