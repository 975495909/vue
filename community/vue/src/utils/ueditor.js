export function loadJsResource(arr = [], baseUrl = '') {
    return new Promise((resolve) => {
        let index = 0;
        arr.map(path => {
            let $script = document.createElement('script');
            $script.src = baseUrl + path;
            document.body.appendChild($script);
            $script.onload = () => {
                if (index == (arr.length - 1)) {
                    resolve();
                }
                index++;
            }
        })
    })
    
}
