# Mkdocs Tutorial

- `mkdocs new .` : Menjadikan folder saat ini Mkdcos, dan akan membuat file `mkdocs.yml`, dan folder `docs/index.md`
- `mkdocs new <nama-project>` : bikin project MkDocs baru
- `mkdocs serve` : jalankan server lokal dengan live reload
- `mkdocs serve --watch` : pantau semua file, lebih agresif dari default
- `mkdocs serve --dirtyreload` : Mode dirty reload (cepat, tapi konfigurasi & tema nggak ke-refresh)
- `mkdocs serve --livereload` : mode live preview mkdocs
- `mkdocs serve -a 0.0.0.0:8000` : serve di semua interface (buat akses dari HP/LAN)

- `mkdocs gh-deploy` : deploy ke GitHub Pages
- `mkdocs gh-deploy --force` : paksa overwrite deployment sebelumnya
- `mkdocs --version` : versi mkdocs

- `mkdocs config` : cek konfigurasi mkdocs.yml
- `mkdocs config -d` : tampilkan config hasil merge (detail mode)

- `mkdocs build --clean` : build dan bersihin output lama
- `mkdocs build --site-dir <path>` : build ke folder custom

- `mkdocs --help` : list semua command MkDocs
- `mkdocs serve --help` : bantuan untuk serve
- `mkdocs build --help` : bantuan untuk build

- `mkdocs plugins list` : lihat plugin yang terinstall
- `mkdocs --quiet` : minimal output (biar terminal nggak berisik)
- `mkdocs --verbose` : debug mode, tampilkan detail build

## pusat bantuan:

- `mkdocs --help`
- `mkdocs serve --help`
- `mkdocs build --help`

- `mkdocs plugins list` : list plugin terinstall
- `mkdocs plugins reload` : Reload plugin tertentu

## Literate nav
Untuk meengatur navbar dan struktur folder dengan baik, lebih baik gunakan plugin pip berupa `mkdocs-literate-nav`. Hasilnya akan jauh lebih rapi.

Aku sempat menghadapi error berkali-kali, karena ternyata children harus memiliki jarak sebanyak 2 tab dari parent. Lihat pada bagian kiri penanda baris, warnanya harus coklat! Ini penting supaya tidak error!