Feature: Audit log component
  Scenario: list audit logs
    When I GET "/audit-logs"
    Then the response status is 200
