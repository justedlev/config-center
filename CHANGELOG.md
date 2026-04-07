# 📦 Changelog

All notable changes to this project will be documented in this file.

This project uses **[Conventional Commits](https://www.conventionalcommits.org/)** and is powered by **[release-please](https://github.com/googleapis/release-please)**.

## [2.0.1](https://github.com/justedlev/config-center/compare/v2.0.0...v2.0.1) (2026-04-07)


### 🧹 Chores

* release 2.0.1 ([31e84a1](https://github.com/justedlev/config-center/commit/31e84a1f79cdaa29f81262999da4b80a5f350947))

## [2.0.0](https://github.com/justedlev/config-center/compare/v1.7.0...v2.0.0) (2026-03-31)


### ⚠ BREAKING CHANGES

* bump java to 21
* migrated to spring boot v4

### 🚀 Features

* add `org.springframework.boot.autoconfigure.AutoConfiguration.imports` ([8cc7e1e](https://github.com/justedlev/config-center/commit/8cc7e1e8e3fda4a2e30fd156b6f5b7830101919c))
* bump java to 21 ([e9a4455](https://github.com/justedlev/config-center/commit/e9a44556828c30ffa393c08609776f2525f64f19))
* **config:** add spring autoconfigure factory ([8fa0779](https://github.com/justedlev/config-center/commit/8fa07797efaa5d3eb651473460dcd02a4ec0dec8))
* migrate to spring boot 4 ([f90bca9](https://github.com/justedlev/config-center/commit/f90bca97f13374083bf3451a66da8679dfd40191))
* migrated to spring boot v4 ([db8dd4c](https://github.com/justedlev/config-center/commit/db8dd4c8cce2710f8e4ced3b95fff664f3e03ddd))
* update security configuration ([c08afdb](https://github.com/justedlev/config-center/commit/c08afdb0824b8eb8cb2f90a5b5614572fb94816e))


### 🐛 Bug Fixes

* **config:** use `ConfigCenterSecurityConfigurationProperties` ([97c257b](https://github.com/justedlev/config-center/commit/97c257bb986d1811c401a5c95abe558f6740cd3b))
* use `ConfigCenterSecurityConfigurationProperties` ([73f4645](https://github.com/justedlev/config-center/commit/73f46457f4922c2589ed0f0e59bf9604a0f74399))


### ♻️ Refactoring

* **props:** update app name ([bcd9447](https://github.com/justedlev/config-center/commit/bcd944768903255cfff4b5ad8515a34b028b8281))
* rename main package to `io.github.justedlev.configcenter` ([fbdb40a](https://github.com/justedlev/config-center/commit/fbdb40a9712841cc720aaf620239579c9f662dc5))
* rename package form `io.justedlev.msrvs.configcenter.autoconfiguration` to `io.justedlev.msrvs.configcenter.autoconfigure` ([363fee6](https://github.com/justedlev/config-center/commit/363fee675289c9c7b82976a9c4e449c722905063))
* rename package from `io.justedlev.msrvs.configcenter.autoconfiguration` to `io.justedlev.msrvs.configcenter.autoconfigure` ([2df56b3](https://github.com/justedlev/config-center/commit/2df56b3e26d8c4d03e3ba568973da476b9a3f86a))


### 📚 Documentation

* update `README.md` ([c2baf58](https://github.com/justedlev/config-center/commit/c2baf5885ff84e9b0ab66799d174e8525f0fff52))

## [1.7.0](https://github.com/justedlev/config-center/compare/v1.6.0...v1.7.0) (2025-10-21)


### 🚀 Features

* **config:** extract `bus` profile ([fcb8c5f](https://github.com/justedlev/config-center/commit/fcb8c5f2721c31faa170c16a93828a2499e2b17a))

## [1.6.0](https://github.com/justedlev/config-center/compare/v1.5.1...v1.6.0) (2025-10-20)


### 🚀 Features

* **config:** improve default config props ([8481336](https://github.com/justedlev/config-center/commit/848133654b430631a3cdc16134e3f50d8ca17f19))
* **config:** improve security config props ([f04079f](https://github.com/justedlev/config-center/commit/f04079f6e4af051f3ac8cbf16b733d9cc938afd1))
* **config:** update profiled configurations ([6b426cd](https://github.com/justedlev/config-center/commit/6b426cda7691f3c402eafda79f2e3faadd8740bb))


### ♻️ Refactoring

* **docker:** rename config dir ([09fac69](https://github.com/justedlev/config-center/commit/09fac69e41075f7ad776c9ccf5e33ba6c669ede3))
* **git:** rename config dir ([e4aec34](https://github.com/justedlev/config-center/commit/e4aec34cd50d11f4daa425f0d9a40010b5dac31c))

## [1.5.1](https://github.com/justedlev/config-center/compare/v1.5.0...v1.5.1) (2025-09-08)


### 🐛 Bug Fixes

* remove `logging` props ([b2de0b5](https://github.com/justedlev/config-center/commit/b2de0b51b234d848c4cc222ee0f8735fb66c4b61))

## [1.5.0](https://github.com/justedlev/config-center/compare/v1.4.2...v1.5.0) (2025-08-08)


### 🚀 Features

* add config monitor ([77b5a04](https://github.com/justedlev/config-center/commit/77b5a04530333cd00143499fb9e0fa4efb5aedb7))

## [1.4.2](https://github.com/justedlev/config-center/compare/v1.4.1...v1.4.2) (2025-05-16)


### 🐛 Bug Fixes

* normalize line endings using `.gitattributes` ([0af5f6a](https://github.com/justedlev/config-center/commit/0af5f6ac91fe611ed6ff9e21728cd2e4e0cb64e4))
* normalize line endings using `.gitattributes` ([6c7d477](https://github.com/justedlev/config-center/commit/6c7d477bbd67e2563e948ea28b35d933fa4e5ff6)), closes [#29](https://github.com/justedlev/config-center/issues/29)

## [1.4.1](https://github.com/justedlev/config-center/compare/v1.4.0...v1.4.1) (2025-04-13)


### 📚 Documentation

* add `CHANGELOG.md` ([3fe017e](https://github.com/justedlev/config-center/commit/3fe017e3aac873c10b134073ac5735110611e2da))
* add `PULL_REQUEST_TEMPLATE.md` ([24a440a](https://github.com/justedlev/config-center/commit/24a440af4f89d9e5e32bcbdf6358878b3d362d36))
* add `SECURITY.md` ([1d8280c](https://github.com/justedlev/config-center/commit/1d8280cd764da2560638acd4cf843b74d58f7a1e))
* **github:** add `ISSUE_TEMPLATE` ([7cfb033](https://github.com/justedlev/config-center/commit/7cfb03308cd27a08c599240f103858c31e25f694))


### 🧪 Tests

* add `application.yml` ([590a2ae](https://github.com/justedlev/config-center/commit/590a2aed0f7015af2e0110e53acaed2f6812e821))

## [Unreleased]

_Changes that are in progress but not yet released._

<!-- RELEASE PLEASE INSERT CHANGELOG HERE -->
