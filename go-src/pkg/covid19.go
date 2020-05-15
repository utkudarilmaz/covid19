package covid19

import (
	"encoding/json"
	"io/ioutil"
	"net/http"
	"strconv"
)

var (
	address = "https://covid19.timsah.cloud"
)

type ResponseMain struct {
	Data struct {
		Confirmed int `json:"confirmed"`
		Deaths    int `json:"deaths"`
		Recovered int `json:"recovered"`
		Active    int `json:"active"`
	} `json:"data"`
	Dt string  `json:"dt"`
	Ts float64 `json:"ts"`
}

func sendRequest(url string) ([]byte, error) {
	url = address + url

	req, err := http.NewRequest("GET", url, nil)
	if err != nil {
		return nil, err
	}

	req.Header.Add("content-type", "application/json")

	res, err := http.DefaultClient.Do(req)
	if err != nil {
		return nil, err
	}

	defer res.Body.Close()
	body, err := ioutil.ReadAll(res.Body)
	if err != nil {
		return nil, err
	}
	return body, nil
}

func TotalDeaths() string {
	body, err := sendRequest("/v2/total")
	if err != nil {
		return "-1"
	}
	var response ResponseMain
	err = json.Unmarshal(body, &response)

	if err != nil {
		return "-1"
	}

	return strconv.Itoa(response.Data.Deaths)
}

func TotalRecovered() string {
	body, err := sendRequest("/v2/total")
	if err != nil {
		return "-1"
	}

	var response ResponseMain
	err = json.Unmarshal(body, &response)

	if err != nil {
		return "-1"
	}

	return strconv.Itoa(response.Data.Recovered)
}

func TotalActive() string {
	body, err := sendRequest("/v2/total")
	if err != nil {
		return "-1"
	}

	var response ResponseMain
	err = json.Unmarshal(body, &response)

	if err != nil {
		return "-1"
	}

	return strconv.Itoa(response.Data.Active)
}

func TotalConfirmed() string {

	body, err := sendRequest("/v2/total")

	if err != nil {
		return "-1"
	}

	var response ResponseMain
	err = json.Unmarshal(body, &response)

	if err != nil {
		return "-1"
	}

	return strconv.Itoa(response.Data.Confirmed)
}

func Total() []byte {
	body, err := sendRequest("/v2/total")
	if err != nil {
		return nil
	}
	return body
}

func Cover(url string) string {

	url = address + url

	req, _ := http.NewRequest("GET", url, nil)
	req.Header.Add("content-type", "application/json")

	res, _ := http.DefaultClient.Do(req)

	defer res.Body.Close()
	body, _ := ioutil.ReadAll(res.Body)

	return string(body)

}
