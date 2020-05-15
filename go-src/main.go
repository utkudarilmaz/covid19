package main

import (
	"fmt"

	"github.com/utkudarilmaz/covid19/pkg"
)

func main() {

	fmt.Println(covid19.GetCountries())
	//
	// res := covid19.Total()
	//
	// var prettyJSON bytes.Buffer
	// error := json.Indent(&prettyJSON, res, "", "\t")
	// if error != nil {
	// 	log.Println("JSON parse error: ", error)
	// 	return
	// }
	//
	// log.Println("CSP Violation:", string(prettyJSON.Bytes()))
}
