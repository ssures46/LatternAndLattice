



<!DOCTYPE html>
<html class="gl-system ui-neutral with-top-bar " lang="en">
<head prefix="og: http://ogp.me/ns#">
<meta charset="utf-8">
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<meta content="width=device-width, initial-scale=1" name="viewport">
<title>src/Npc.java · main · courses / 2025 / 09 / COMPSCI2212 / group16 · GitLab</title>
<script>
//<![CDATA[
window.gon={};gon.features={"duoSideRail":false,"inlineBlame":false,"directoryCodeDropdownUpdates":true,"repositoryFileTreeBrowser":false,"blobEditRefactor":false};gon.math_rendering_limits_enabled=true;
//]]>
</script>
<script>
//<![CDATA[
window.uploads_path = "/courses/2025/09/COMPSCI2212/group16/uploads";



//]]>
</script>
<script>
//<![CDATA[
const root = document.documentElement;
if (window.matchMedia('(prefers-color-scheme: dark)').matches) {
  root.classList.add('gl-dark');
}

window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', (e) => {
  if (e.matches) {
    root.classList.add('gl-dark');
  } else {
    root.classList.remove('gl-dark');
  }
});

//]]>
</script>
<script>
//<![CDATA[
var gl = window.gl || {};
gl.startup_calls = null;
gl.startup_graphql_calls = [{"query":"query getBlobInfo(\n  $projectPath: ID!\n  $filePath: [String!]!\n  $ref: String!\n  $refType: RefType\n  $shouldFetchRawText: Boolean!\n) {\n  project(fullPath: $projectPath) {\n    __typename\n    id\n    repository {\n      __typename\n      empty\n      blobs(paths: $filePath, ref: $ref, refType: $refType) {\n        __typename\n        nodes {\n          __typename\n          id\n          webPath\n          name\n          size\n          rawSize\n          rawTextBlob @include(if: $shouldFetchRawText)\n          fileType\n          language\n          path\n          blamePath\n          editBlobPath\n          gitpodBlobUrl\n          ideEditPath\n          forkAndEditPath\n          ideForkAndEditPath\n          codeNavigationPath\n          projectBlobPathRoot\n          forkAndViewPath\n          environmentFormattedExternalUrl\n          environmentExternalUrlForRouteMap\n          canModifyBlob\n          canModifyBlobWithWebIde\n          canCurrentUserPushToBranch\n          archived\n          storedExternally\n          externalStorage\n          externalStorageUrl\n          rawPath\n          replacePath\n          pipelineEditorPath\n          simpleViewer {\n            fileType\n            tooLarge\n            type\n            renderError\n          }\n          richViewer {\n            fileType\n            tooLarge\n            type\n            renderError\n          }\n        }\n      }\n    }\n  }\n}\n","variables":{"projectPath":"courses/2025/09/COMPSCI2212/group16","ref":"main","refType":"HEADS","filePath":"src/Npc.java","shouldFetchRawText":true}}];

if (gl.startup_calls && window.fetch) {
  Object.keys(gl.startup_calls).forEach(apiCall => {
   gl.startup_calls[apiCall] = {
      fetchCall: fetch(apiCall, {
        // Emulate XHR for Rails AJAX request checks
        headers: {
          'X-Requested-With': 'XMLHttpRequest'
        },
        // fetch won’t send cookies in older browsers, unless you set the credentials init option.
        // We set to `same-origin` which is default value in modern browsers.
        // See https://github.com/whatwg/fetch/pull/585 for more information.
        credentials: 'same-origin'
      })
    };
  });
}
if (gl.startup_graphql_calls && window.fetch) {
  const headers = {"X-CSRF-Token":"YTBSFGfYfhiREwQJZIprAzI7oteyXOJdZZfLvXmnVSYyUlJ4LhkHpGRSm7VTkNI604AYTWX7l0_se_Uz8jTvxw","x-gitlab-feature-category":"source_code_management"};
  const url = `https://gitlab.sci.uwo.ca/api/graphql`

  const opts = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      ...headers,
    }
  };

  gl.startup_graphql_calls = gl.startup_graphql_calls.map(call => ({
    ...call,
    fetchCall: fetch(url, {
      ...opts,
      credentials: 'same-origin',
      body: JSON.stringify(call)
    })
  }))
}


//]]>
</script>

<link rel="prefetch" href="/assets/webpack/monaco.2f50fc5f.chunk.js">

<meta content="light dark" name="color-scheme">
<link rel="stylesheet" href="/assets/application-8a9f6b466d0b36fa2755d3040da2a3fae715d40a0a0f10b541aa3e9b59e96ccc.css" media="(prefers-color-scheme: light)" />
<link rel="stylesheet" href="/assets/application_dark-8030447b34f738da83c7d723089105262c01eb13b5b3d30c4c5a3c41520806d7.css" media="(prefers-color-scheme: dark)" />
<link rel="stylesheet" href="/assets/page_bundles/tree-c8b2da86bd9f1872e6c1dc4ce2c95ee33b47a7181079717517d2a6cbe182fbf0.css" /><link rel="stylesheet" href="/assets/page_bundles/projects-6c68dbce82c8ad08ca756be341b31c8cb69af645ef5c77d4f873fa7df3002537.css" /><link rel="stylesheet" href="/assets/page_bundles/commit_description-9e7efe20f0cef17d0606edabfad0418e9eb224aaeaa2dae32c817060fa60abcc.css" /><link rel="stylesheet" href="/assets/page_bundles/work_items-9c736722b9bb76f2c57757ed1bae3a5e79456e78cba897d309ea0aab9503330c.css" /><link rel="stylesheet" href="/assets/page_bundles/notes_shared-57a4db43ec755df32d86f7c5cdd49148a3b4135f33e08d3eee766fa405736239.css" />
<link rel="stylesheet" href="/assets/application_utilities-e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855.css" media="(prefers-color-scheme: light)" />
<link rel="stylesheet" href="/assets/application_utilities_dark-e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855.css" media="(prefers-color-scheme: dark)" />
<link rel="stylesheet" href="/assets/tailwind-a9fde9b8ef840a75f25766bd8ab8b53262d10d4a4925fa5da3838923cf8e482f.css" />


<link rel="stylesheet" href="/assets/fonts-deb7ad1d55ca77c0172d8538d53442af63604ff490c74acc2859db295c125bdb.css" />
<link rel="stylesheet" href="/assets/highlight/themes/white-be4fdae1a25a255ed59f155dc9b7449697d7552a98dd3965643301056c8f426a.css" media="(prefers-color-scheme: light)" />
<link rel="stylesheet" href="/assets/highlight/themes/dark-77ed54f14352adb6ae26be56730f321e0273576f8be6e1f73d1d2ac3ec191a0b.css" media="(prefers-color-scheme: dark)" />

<script src="/assets/webpack/runtime.db496bf3.bundle.js" defer="defer"></script>
<script src="/assets/webpack/main.2ca46d11.chunk.js" defer="defer"></script>
<script src="/assets/webpack/tracker.4ac2efa2.chunk.js" defer="defer"></script>
<script>
//<![CDATA[
window.snowplowOptions = {"namespace":"gl","hostname":"gitlab.sci.uwo.ca:443","postPath":"/-/collect_events","forceSecureTracker":true,"appId":"gitlab_sm"}

gl = window.gl || {};
gl.snowplowStandardContext = {"schema":"iglu:com.gitlab/gitlab_standard/jsonschema/1-1-7","data":{"environment":"self-managed","source":"gitlab-rails","correlation_id":"01KBNSGEQ2TTEG9WZZ72WF0CYS","plan":"free","extra":{},"user_id":"a5wF32uTSwbsLgQFwizI3I6nCKbmV9RiXN1+xf1gyoQ=","global_user_id":"SqVlc0dydtgn48vwSe/4Gb2gpSLm8F/tstt1wBtHuFs=","user_type":"human","is_gitlab_team_member":null,"namespace_id":5985,"ultimate_parent_namespace_id":132,"project_id":4405,"feature_enabled_by_namespace_ids":null,"realm":"self-managed","instance_id":"5a96c8ed-b36c-4deb-9ba7-8dfe9ea01ef8","unique_instance_id":"b2256a90-ae5a-54a1-83d4-29aefcadd498","host_name":"gitlab.sci.uwo.ca","instance_version":"18.5.2","context_generated_at":"2025-12-04T23:00:13.810Z"}}
gl.snowplowPseudonymizedPageUrl = "https://gitlab.sci.uwo.ca/namespace5985/project4405/-/blob/:repository_path?ref_type=masked_ref_type";
gl.maskedDefaultReferrerUrl = null;
gl.ga4MeasurementId = 'G-ENFH3X7M5Y';
gl.duoEvents = [];
gl.onlySendDuoEvents = true;


//]]>
</script>
<link rel="preload" href="/assets/application_utilities-e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855.css" as="style" type="text/css">
<link rel="preload" href="/assets/application-8a9f6b466d0b36fa2755d3040da2a3fae715d40a0a0f10b541aa3e9b59e96ccc.css" as="style" type="text/css">
<link rel="preload" href="/assets/highlight/themes/white-be4fdae1a25a255ed59f155dc9b7449697d7552a98dd3965643301056c8f426a.css" as="style" type="text/css">




<script src="/assets/webpack/commons-pages.groups.new-pages.import.gitlab_projects.new-pages.import.manifest.new-pages.projects.n-44c6c18e.3cf8684d.chunk.js" defer="defer"></script>
<script src="/assets/webpack/commons-pages.search.show-super_sidebar.c76916f4.chunk.js" defer="defer"></script>
<script src="/assets/webpack/super_sidebar.40335732.chunk.js" defer="defer"></script>
<script src="/assets/webpack/commons-pages.projects-pages.projects.activity-pages.projects.alert_management.details-pages.project-2e472f70.e1b4e5a0.chunk.js" defer="defer"></script>
<script src="/assets/webpack/17193943.3d5c0e15.chunk.js" defer="defer"></script>
<script src="/assets/webpack/commons-pages.groups.packages-pages.groups.registry.repositories-pages.projects.blob.show-pages.proj-5dce5667.6347520c.chunk.js" defer="defer"></script>
<script src="/assets/webpack/commons-pages.projects.blob.show-pages.projects.branches.new-pages.projects.commits.show-pages.proje-81161c0b.37bbe678.chunk.js" defer="defer"></script>
<script src="/assets/webpack/commons-pages.import.bitbucket_server.new-pages.import.gitea.new-pages.import.gitlab_projects.new-pa-7a549248.df49ec20.chunk.js" defer="defer"></script>
<script src="/assets/webpack/dbe6a049.8c51c52f.chunk.js" defer="defer"></script>
<script src="/assets/webpack/commons-pages.projects.blob.show-pages.projects.show-pages.projects.snippets.edit-pages.projects.sni-42df7d4c.3a43e86e.chunk.js" defer="defer"></script>
<script src="/assets/webpack/commons-pages.projects.blob.show-pages.projects.show-pages.projects.snippets.show-pages.projects.tre-c684fcf6.6831d9b8.chunk.js" defer="defer"></script>
<script src="/assets/webpack/commons-pages.projects.blob.edit-pages.projects.blob.new-pages.projects.blob.show-pages.projects.sho-ec79e51c.2a8f769a.chunk.js" defer="defer"></script>
<script src="/assets/webpack/commons-pages.projects.blob.show-pages.projects.commits.show-pages.projects.show-pages.projects.tree.show.7f54bbc2.chunk.js" defer="defer"></script>
<script src="/assets/webpack/commons-pages.projects.blob.show-pages.projects.show-pages.projects.tree.show-pages.search.show.d1fa2a17.chunk.js" defer="defer"></script>
<script src="/assets/webpack/commons-pages.projects.blame.show-pages.projects.blob.show-pages.projects.show-pages.projects.tree.show.074f3747.chunk.js" defer="defer"></script>
<script src="/assets/webpack/commons-pages.projects.blob.show-pages.projects.show-pages.projects.tree.show.7fff4a19.chunk.js" defer="defer"></script>
<script src="/assets/webpack/commons-pages.projects.blob.show-pages.projects.tree.show-treeList.eff359d3.chunk.js" defer="defer"></script>
<script src="/assets/webpack/pages.projects.blob.show.cabbc6a5.chunk.js" defer="defer"></script>

<meta content="object" property="og:type">
<meta content="GitLab" property="og:site_name">
<meta content="src/Npc.java · main · courses / 2025 / 09 / COMPSCI2212 / group16 · GitLab" property="og:title">
<meta content="GitLab Community Edition" property="og:description">
<meta content="https://gitlab.sci.uwo.ca/assets/twitter_card-570ddb06edf56a2312253c5872489847a0f385112ddbcd71ccfa1570febab5d2.jpg" property="og:image">
<meta content="64" property="og:image:width">
<meta content="64" property="og:image:height">
<meta content="https://gitlab.sci.uwo.ca/courses/2025/09/COMPSCI2212/group16/-/blob/main/src/Npc.java?ref_type=heads" property="og:url">
<meta content="summary" property="twitter:card">
<meta content="src/Npc.java · main · courses / 2025 / 09 / COMPSCI2212 / group16 · GitLab" property="twitter:title">
<meta content="GitLab Community Edition" property="twitter:description">
<meta content="https://gitlab.sci.uwo.ca/assets/twitter_card-570ddb06edf56a2312253c5872489847a0f385112ddbcd71ccfa1570febab5d2.jpg" property="twitter:image">

<meta name="csrf-param" content="authenticity_token" />
<meta name="csrf-token" content="7P-HERNk5vw7wYfAINfKnvCli18i16qtSaB2O9e5Pwm_nYd9WqWfQM6AGHwXzXOnER4xxfVw37_ATEi1XCqF6A" />
<meta name="csp-nonce" />
<meta name="action-cable-url" content="/-/cable" />
<link href="/-/manifest.json" rel="manifest">
<link rel="icon" type="image/png" href="/uploads/-/system/appearance/favicon/1/favicon.ico" id="favicon" data-original-href="/uploads/-/system/appearance/favicon/1/favicon.ico" />
<link rel="apple-touch-icon" type="image/x-icon" href="/assets/apple-touch-icon-b049d4bc0dd9626f31db825d61880737befc7835982586d015bded10b4435460.png" />
<link href="/search/opensearch.xml" rel="search" title="Search GitLab" type="application/opensearchdescription+xml">




<meta content="GitLab Community Edition" name="description">
<meta content="#ececef" name="theme-color">
</head>

<body class="tab-width-8 gl-browser-safari gl-platform-mac body-fixed-scrollbar" data-group="COMPSCI2212" data-group-full-path="courses/2025/09/COMPSCI2212" data-namespace-id="5985" data-page="projects:blob:show" data-page-type-id="main/src/Npc.java" data-project="group16" data-project-full-path="courses/2025/09/COMPSCI2212/group16" data-project-id="4405" data-project-studio-available="false" data-project-studio-enabled="false">
<div id="js-tooltips-container"></div>

<script>
//<![CDATA[
gl = window.gl || {};
gl.client = {"isSafari":true,"isMac":true};


//]]>
</script>


<div class="layout-page page-with-super-sidebar">
<aside class="js-super-sidebar super-sidebar super-sidebar-loading" data-command-palette="{&quot;project_files_url&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/files/main?format=json&quot;,&quot;project_blob_url&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/blob/main&quot;}" data-force-desktop-expanded-sidebar="" data-is-saas="false" data-root-path="/" data-sidebar="{&quot;whats_new_most_recent_release_items_count&quot;:4,&quot;whats_new_version_digest&quot;:&quot;dfb6b8c404e0fa2b32def4ba49e00b3c6064721d6feb7ca7856daf80766deec7&quot;,&quot;whats_new_read_articles&quot;:[],&quot;whats_new_mark_as_read_path&quot;:&quot;/-/whats_new/mark_as_read&quot;,&quot;is_logged_in&quot;:true,&quot;compare_plans_url&quot;:&quot;https://about.gitlab.com/pricing&quot;,&quot;context_switcher_links&quot;:[{&quot;title&quot;:&quot;Your work&quot;,&quot;link&quot;:&quot;/&quot;,&quot;icon&quot;:&quot;work&quot;},{&quot;title&quot;:&quot;Explore&quot;,&quot;link&quot;:&quot;/explore&quot;,&quot;icon&quot;:&quot;compass&quot;},{&quot;title&quot;:&quot;Profile&quot;,&quot;link&quot;:&quot;/-/user_settings/profile&quot;,&quot;icon&quot;:&quot;profile&quot;},{&quot;title&quot;:&quot;Preferences&quot;,&quot;link&quot;:&quot;/-/profile/preferences&quot;,&quot;icon&quot;:&quot;preferences&quot;}],&quot;current_menu_items&quot;:[{&quot;id&quot;:&quot;project_overview&quot;,&quot;title&quot;:&quot;group16&quot;,&quot;entity_id&quot;:4405,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16&quot;,&quot;link_classes&quot;:&quot;shortcuts-project&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;manage_menu&quot;,&quot;title&quot;:&quot;Manage&quot;,&quot;icon&quot;:&quot;users&quot;,&quot;avatar_shape&quot;:&quot;rect&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/activity&quot;,&quot;is_active&quot;:false,&quot;items&quot;:[{&quot;id&quot;:&quot;activity&quot;,&quot;title&quot;:&quot;Activity&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/activity&quot;,&quot;link_classes&quot;:&quot;shortcuts-project-activity&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;members&quot;,&quot;title&quot;:&quot;Members&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/project_members&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;labels&quot;,&quot;title&quot;:&quot;Labels&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/labels&quot;,&quot;is_active&quot;:false}],&quot;separated&quot;:false},{&quot;id&quot;:&quot;plan_menu&quot;,&quot;title&quot;:&quot;Plan&quot;,&quot;icon&quot;:&quot;planning&quot;,&quot;avatar_shape&quot;:&quot;rect&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/issues&quot;,&quot;is_active&quot;:false,&quot;items&quot;:[{&quot;id&quot;:&quot;project_issue_list&quot;,&quot;title&quot;:&quot;Issues&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/issues&quot;,&quot;link_classes&quot;:&quot;shortcuts-issues has-sub-items&quot;,&quot;pill_count_field&quot;:&quot;openIssuesCount&quot;,&quot;pill_count_dynamic&quot;:false,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;boards&quot;,&quot;title&quot;:&quot;Issue boards&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/boards&quot;,&quot;link_classes&quot;:&quot;shortcuts-issue-boards&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;milestones&quot;,&quot;title&quot;:&quot;Milestones&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/milestones&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;project_wiki&quot;,&quot;title&quot;:&quot;Wiki&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/wikis/home&quot;,&quot;link_classes&quot;:&quot;shortcuts-wiki&quot;,&quot;is_active&quot;:false}],&quot;separated&quot;:false},{&quot;id&quot;:&quot;code_menu&quot;,&quot;title&quot;:&quot;Code&quot;,&quot;icon&quot;:&quot;code&quot;,&quot;avatar_shape&quot;:&quot;rect&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/merge_requests&quot;,&quot;is_active&quot;:true,&quot;items&quot;:[{&quot;id&quot;:&quot;project_merge_request_list&quot;,&quot;title&quot;:&quot;Merge requests&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/merge_requests&quot;,&quot;link_classes&quot;:&quot;shortcuts-merge_requests&quot;,&quot;pill_count_field&quot;:&quot;openMergeRequestsCount&quot;,&quot;pill_count_dynamic&quot;:false,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;files&quot;,&quot;title&quot;:&quot;Repository&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/tree/main&quot;,&quot;link_classes&quot;:&quot;shortcuts-tree&quot;,&quot;is_active&quot;:true},{&quot;id&quot;:&quot;branches&quot;,&quot;title&quot;:&quot;Branches&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/branches&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;commits&quot;,&quot;title&quot;:&quot;Commits&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/commits/main?ref_type=heads&quot;,&quot;link_classes&quot;:&quot;shortcuts-commits&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;tags&quot;,&quot;title&quot;:&quot;Tags&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/tags&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;graphs&quot;,&quot;title&quot;:&quot;Repository graph&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/network/main?ref_type=heads&quot;,&quot;link_classes&quot;:&quot;shortcuts-network&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;compare&quot;,&quot;title&quot;:&quot;Compare revisions&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/compare?from=main\u0026to=main&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;project_snippets&quot;,&quot;title&quot;:&quot;Snippets&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/snippets&quot;,&quot;link_classes&quot;:&quot;shortcuts-snippets&quot;,&quot;is_active&quot;:false}],&quot;separated&quot;:false},{&quot;id&quot;:&quot;build_menu&quot;,&quot;title&quot;:&quot;Build&quot;,&quot;icon&quot;:&quot;rocket&quot;,&quot;avatar_shape&quot;:&quot;rect&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/pipelines&quot;,&quot;is_active&quot;:false,&quot;items&quot;:[{&quot;id&quot;:&quot;pipelines&quot;,&quot;title&quot;:&quot;Pipelines&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/pipelines&quot;,&quot;link_classes&quot;:&quot;shortcuts-pipelines&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;jobs&quot;,&quot;title&quot;:&quot;Jobs&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/jobs&quot;,&quot;link_classes&quot;:&quot;shortcuts-builds&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;pipelines_editor&quot;,&quot;title&quot;:&quot;Pipeline editor&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/ci/editor?branch_name=main&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;pipeline_schedules&quot;,&quot;title&quot;:&quot;Pipeline schedules&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/pipeline_schedules&quot;,&quot;link_classes&quot;:&quot;shortcuts-builds&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;artifacts&quot;,&quot;title&quot;:&quot;Artifacts&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/artifacts&quot;,&quot;link_classes&quot;:&quot;shortcuts-builds&quot;,&quot;is_active&quot;:false}],&quot;separated&quot;:false},{&quot;id&quot;:&quot;secure_menu&quot;,&quot;title&quot;:&quot;Secure&quot;,&quot;icon&quot;:&quot;shield&quot;,&quot;avatar_shape&quot;:&quot;rect&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/security/configuration&quot;,&quot;is_active&quot;:false,&quot;items&quot;:[{&quot;id&quot;:&quot;configuration&quot;,&quot;title&quot;:&quot;Security configuration&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/security/configuration&quot;,&quot;is_active&quot;:false}],&quot;separated&quot;:false},{&quot;id&quot;:&quot;deploy_menu&quot;,&quot;title&quot;:&quot;Deploy&quot;,&quot;icon&quot;:&quot;deployments&quot;,&quot;avatar_shape&quot;:&quot;rect&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/releases&quot;,&quot;is_active&quot;:false,&quot;items&quot;:[{&quot;id&quot;:&quot;releases&quot;,&quot;title&quot;:&quot;Releases&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/releases&quot;,&quot;link_classes&quot;:&quot;shortcuts-deployments-releases&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;feature_flags&quot;,&quot;title&quot;:&quot;Feature flags&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/feature_flags&quot;,&quot;link_classes&quot;:&quot;shortcuts-feature-flags&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;packages_registry&quot;,&quot;title&quot;:&quot;Package registry&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/packages&quot;,&quot;link_classes&quot;:&quot;shortcuts-container-registry&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;model_registry&quot;,&quot;title&quot;:&quot;Model registry&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/ml/models&quot;,&quot;is_active&quot;:false}],&quot;separated&quot;:false},{&quot;id&quot;:&quot;operations_menu&quot;,&quot;title&quot;:&quot;Operate&quot;,&quot;icon&quot;:&quot;cloud-pod&quot;,&quot;avatar_shape&quot;:&quot;rect&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/environments&quot;,&quot;is_active&quot;:false,&quot;items&quot;:[{&quot;id&quot;:&quot;environments&quot;,&quot;title&quot;:&quot;Environments&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/environments&quot;,&quot;link_classes&quot;:&quot;shortcuts-environments&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;kubernetes&quot;,&quot;title&quot;:&quot;Kubernetes clusters&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/clusters&quot;,&quot;link_classes&quot;:&quot;shortcuts-kubernetes&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;terraform_states&quot;,&quot;title&quot;:&quot;Terraform states&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/terraform&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;infrastructure_registry&quot;,&quot;title&quot;:&quot;Terraform modules&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/terraform_module_registry&quot;,&quot;is_active&quot;:false}],&quot;separated&quot;:false},{&quot;id&quot;:&quot;monitor_menu&quot;,&quot;title&quot;:&quot;Monitor&quot;,&quot;icon&quot;:&quot;monitor&quot;,&quot;avatar_shape&quot;:&quot;rect&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/error_tracking&quot;,&quot;is_active&quot;:false,&quot;items&quot;:[{&quot;id&quot;:&quot;error_tracking&quot;,&quot;title&quot;:&quot;Error Tracking&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/error_tracking&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;alert_management&quot;,&quot;title&quot;:&quot;Alerts&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/alert_management&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;incidents&quot;,&quot;title&quot;:&quot;Incidents&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/incidents&quot;,&quot;is_active&quot;:false}],&quot;separated&quot;:false},{&quot;id&quot;:&quot;analyze_menu&quot;,&quot;title&quot;:&quot;Analyze&quot;,&quot;icon&quot;:&quot;chart&quot;,&quot;avatar_shape&quot;:&quot;rect&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/value_stream_analytics&quot;,&quot;is_active&quot;:false,&quot;items&quot;:[{&quot;id&quot;:&quot;cycle_analytics&quot;,&quot;title&quot;:&quot;Value stream analytics&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/value_stream_analytics&quot;,&quot;link_classes&quot;:&quot;shortcuts-project-cycle-analytics&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;contributors&quot;,&quot;title&quot;:&quot;Contributor analytics&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/graphs/main?ref_type=heads&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;ci_cd_analytics&quot;,&quot;title&quot;:&quot;CI/CD analytics&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/pipelines/charts&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;repository_analytics&quot;,&quot;title&quot;:&quot;Repository analytics&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/graphs/main/charts&quot;,&quot;link_classes&quot;:&quot;shortcuts-repository-charts&quot;,&quot;is_active&quot;:false},{&quot;id&quot;:&quot;model_experiments&quot;,&quot;title&quot;:&quot;Model experiments&quot;,&quot;link&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/ml/experiments&quot;,&quot;is_active&quot;:false}],&quot;separated&quot;:false}],&quot;current_context_header&quot;:&quot;Project&quot;,&quot;support_path&quot;:&quot;https://help.sci.uwo.ca/servicedesk/customer/portal/2/create/374&quot;,&quot;docs_path&quot;:&quot;/help/docs&quot;,&quot;display_whats_new&quot;:true,&quot;show_version_check&quot;:false,&quot;search&quot;:{&quot;search_path&quot;:&quot;/search&quot;,&quot;issues_path&quot;:&quot;/dashboard/issues&quot;,&quot;mr_path&quot;:&quot;/dashboard/merge_requests&quot;,&quot;autocomplete_path&quot;:&quot;/search/autocomplete&quot;,&quot;settings_path&quot;:&quot;/search/settings&quot;,&quot;search_context&quot;:{&quot;group&quot;:{&quot;id&quot;:5985,&quot;name&quot;:&quot;COMPSCI2212&quot;,&quot;full_name&quot;:&quot;courses / 2025 / 09 / COMPSCI2212&quot;},&quot;group_metadata&quot;:{&quot;issues_path&quot;:&quot;/groups/courses/2025/09/COMPSCI2212/-/issues&quot;,&quot;mr_path&quot;:&quot;/groups/courses/2025/09/COMPSCI2212/-/merge_requests&quot;},&quot;project&quot;:{&quot;id&quot;:4405,&quot;name&quot;:&quot;group16&quot;},&quot;project_metadata&quot;:{&quot;mr_path&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/merge_requests&quot;,&quot;issues_path&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/issues&quot;},&quot;code_search&quot;:true,&quot;ref&quot;:&quot;main&quot;,&quot;scope&quot;:null,&quot;for_snippets&quot;:null}},&quot;panel_type&quot;:&quot;project&quot;,&quot;shortcut_links&quot;:[{&quot;title&quot;:&quot;Milestones&quot;,&quot;href&quot;:&quot;/dashboard/milestones&quot;,&quot;css_class&quot;:&quot;dashboard-shortcuts-milestones&quot;},{&quot;title&quot;:&quot;Snippets&quot;,&quot;href&quot;:&quot;/dashboard/snippets&quot;,&quot;css_class&quot;:&quot;dashboard-shortcuts-snippets&quot;},{&quot;title&quot;:&quot;Activity&quot;,&quot;href&quot;:&quot;/dashboard/activity&quot;,&quot;css_class&quot;:&quot;dashboard-shortcuts-activity&quot;},{&quot;title&quot;:&quot;Groups&quot;,&quot;href&quot;:&quot;/dashboard/groups&quot;,&quot;css_class&quot;:&quot;dashboard-shortcuts-groups&quot;},{&quot;title&quot;:&quot;Projects&quot;,&quot;href&quot;:&quot;/dashboard/projects&quot;,&quot;css_class&quot;:&quot;dashboard-shortcuts-projects&quot;},{&quot;title&quot;:&quot;Create a new issue&quot;,&quot;href&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/issues/new&quot;,&quot;css_class&quot;:&quot;shortcuts-new-issue&quot;}],&quot;terms&quot;:&quot;/-/users/terms&quot;,&quot;is_admin&quot;:false,&quot;name&quot;:&quot;Sivaramchandar Suresh&quot;,&quot;username&quot;:&quot;ssures46&quot;,&quot;admin_url&quot;:&quot;/admin&quot;,&quot;admin_mode&quot;:{&quot;admin_mode_feature_enabled&quot;:false,&quot;admin_mode_active&quot;:false,&quot;enter_admin_mode_url&quot;:&quot;/admin/session/new&quot;,&quot;leave_admin_mode_url&quot;:&quot;/admin/session/destroy&quot;,&quot;user_is_admin&quot;:false},&quot;avatar_url&quot;:&quot;https://secure.gravatar.com/avatar/5149fd1b72125a8b1ec3d3056a610f9bf12ee24efe650860786a1f9cb07e43e9?s=80\u0026d=identicon&quot;,&quot;has_link_to_profile&quot;:true,&quot;link_to_profile&quot;:&quot;/ssures46&quot;,&quot;logo_url&quot;:&quot;/uploads/-/system/appearance/header_logo/1/Sci-Stacked-Regular.svg&quot;,&quot;status&quot;:{&quot;can_update&quot;:true,&quot;busy&quot;:null,&quot;customized&quot;:null,&quot;availability&quot;:&quot;&quot;,&quot;emoji&quot;:null,&quot;message_html&quot;:null,&quot;message&quot;:null,&quot;clear_after&quot;:null},&quot;settings&quot;:{&quot;has_settings&quot;:true,&quot;profile_path&quot;:&quot;/-/user_settings/profile&quot;,&quot;profile_preferences_path&quot;:&quot;/-/profile/preferences&quot;},&quot;user_counts&quot;:{&quot;assigned_issues&quot;:0,&quot;assigned_merge_requests&quot;:0,&quot;review_requested_merge_requests&quot;:0,&quot;todos&quot;:0,&quot;last_update&quot;:1764889213857},&quot;can_sign_out&quot;:true,&quot;sign_out_link&quot;:&quot;/users/sign_out&quot;,&quot;issues_dashboard_path&quot;:&quot;/dashboard/issues?assignee_username=ssures46&quot;,&quot;merge_request_dashboard_path&quot;:&quot;/dashboard/merge_requests&quot;,&quot;todos_dashboard_path&quot;:&quot;/dashboard/todos&quot;,&quot;create_new_menu_groups&quot;:[{&quot;name&quot;:&quot;In this project&quot;,&quot;items&quot;:[{&quot;text&quot;:&quot;New issue&quot;,&quot;href&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/issues/new&quot;,&quot;component&quot;:&quot;create_new_work_item_modal&quot;,&quot;extraAttrs&quot;:{&quot;data-track-label&quot;:&quot;new_issue&quot;,&quot;data-track-action&quot;:&quot;click_link&quot;,&quot;data-track-property&quot;:&quot;nav_create_menu&quot;,&quot;data-testid&quot;:&quot;create_menu_item&quot;,&quot;data-qa-create-menu-item&quot;:&quot;new_issue&quot;}},{&quot;text&quot;:&quot;New merge request&quot;,&quot;href&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/merge_requests/new&quot;,&quot;component&quot;:null,&quot;extraAttrs&quot;:{&quot;data-track-label&quot;:&quot;new_mr&quot;,&quot;data-track-action&quot;:&quot;click_link&quot;,&quot;data-track-property&quot;:&quot;nav_create_menu&quot;,&quot;data-testid&quot;:&quot;create_menu_item&quot;,&quot;data-qa-create-menu-item&quot;:&quot;new_mr&quot;}},{&quot;text&quot;:&quot;New snippet&quot;,&quot;href&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/snippets/new&quot;,&quot;component&quot;:null,&quot;extraAttrs&quot;:{&quot;data-track-label&quot;:&quot;new_snippet&quot;,&quot;data-track-action&quot;:&quot;click_link&quot;,&quot;data-track-property&quot;:&quot;nav_create_menu&quot;,&quot;data-testid&quot;:&quot;create_menu_item&quot;,&quot;data-qa-create-menu-item&quot;:&quot;new_snippet&quot;}}]},{&quot;name&quot;:&quot;In GitLab&quot;,&quot;items&quot;:[{&quot;text&quot;:&quot;New project/repository&quot;,&quot;href&quot;:&quot;/projects/new&quot;,&quot;component&quot;:null,&quot;extraAttrs&quot;:{&quot;data-track-label&quot;:&quot;general_new_project&quot;,&quot;data-track-action&quot;:&quot;click_link&quot;,&quot;data-track-property&quot;:&quot;nav_create_menu&quot;,&quot;data-testid&quot;:&quot;create_menu_item&quot;,&quot;data-qa-create-menu-item&quot;:&quot;general_new_project&quot;}},{&quot;text&quot;:&quot;New snippet&quot;,&quot;href&quot;:&quot;/-/snippets/new&quot;,&quot;component&quot;:null,&quot;extraAttrs&quot;:{&quot;data-track-label&quot;:&quot;general_new_snippet&quot;,&quot;data-track-action&quot;:&quot;click_link&quot;,&quot;data-track-property&quot;:&quot;nav_create_menu&quot;,&quot;data-testid&quot;:&quot;create_menu_item&quot;,&quot;data-qa-create-menu-item&quot;:&quot;general_new_snippet&quot;}}]}],&quot;projects_path&quot;:&quot;/dashboard/projects&quot;,&quot;groups_path&quot;:&quot;/dashboard/groups&quot;,&quot;gitlab_com_but_not_canary&quot;:false,&quot;gitlab_com_and_canary&quot;:false,&quot;canary_toggle_com_url&quot;:&quot;https://next.gitlab.com&quot;,&quot;current_context&quot;:{&quot;namespace&quot;:&quot;projects&quot;,&quot;item&quot;:{&quot;id&quot;:4405,&quot;name&quot;:&quot;group16&quot;,&quot;namespace&quot;:&quot;courses / 2025 / 09 / COMPSCI2212 / group16&quot;,&quot;fullPath&quot;:&quot;courses/2025/09/COMPSCI2212/group16&quot;,&quot;webUrl&quot;:&quot;/courses/2025/09/COMPSCI2212/group16&quot;,&quot;avatarUrl&quot;:null}},&quot;pinned_items&quot;:[&quot;project_issue_list&quot;,&quot;project_merge_request_list&quot;],&quot;update_pins_url&quot;:&quot;/-/users/pins&quot;,&quot;is_impersonating&quot;:false,&quot;stop_impersonation_path&quot;:&quot;/admin/impersonation&quot;,&quot;track_visits_path&quot;:&quot;/-/track_namespace_visits&quot;,&quot;work_items&quot;:{&quot;full_path&quot;:&quot;courses/2025/09/COMPSCI2212/group16&quot;,&quot;has_issuable_health_status_feature&quot;:&quot;false&quot;,&quot;issues_list_path&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/issues&quot;,&quot;labels_manage_path&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/labels&quot;,&quot;can_admin_label&quot;:&quot;true&quot;,&quot;has_issue_weights_feature&quot;:&quot;false&quot;,&quot;has_iterations_feature&quot;:&quot;false&quot;,&quot;work_item_planning_view_enabled&quot;:&quot;false&quot;}}"></aside>


<div class="content-wrapper">
<div class="broadcast-wrapper">



</div>
<div class="alert-wrapper alert-wrapper-top-space gl-flex gl-flex-col gl-gap-3 container-fluid container-limited">



























</div>

<div class="top-bar-fixed container-fluid" data-testid="top-bar">
<div class="top-bar-container gl-flex gl-items-center gl-gap-2">
<div class="gl-grow gl-basis-0 gl-flex gl-items-center gl-justify-start gl-gap-3">
<button class="gl-button btn btn-icon btn-md btn-default btn-default-tertiary js-super-sidebar-toggle-expand super-sidebar-toggle -gl-ml-3" aria-controls="super-sidebar" aria-expanded="false" aria-label="Primary navigation sidebar" type="button"><svg class="s16 gl-icon gl-button-icon " data-testid="sidebar-icon"><use href="/assets/icons-62cd41f10569bb5050df02409792752f47c042aa91f8d59f11b48b79e724f90d.svg#sidebar"></use></svg>

</button>
<script type="application/ld+json">
{"@context":"https://schema.org","@type":"BreadcrumbList","itemListElement":[{"@type":"ListItem","position":1,"name":"courses","item":"https://gitlab.sci.uwo.ca/courses"},{"@type":"ListItem","position":2,"name":"2025","item":"https://gitlab.sci.uwo.ca/courses/2025"},{"@type":"ListItem","position":3,"name":"09","item":"https://gitlab.sci.uwo.ca/courses/2025/09"},{"@type":"ListItem","position":4,"name":"COMPSCI2212","item":"https://gitlab.sci.uwo.ca/courses/2025/09/COMPSCI2212"},{"@type":"ListItem","position":5,"name":"group16","item":"https://gitlab.sci.uwo.ca/courses/2025/09/COMPSCI2212/group16"},{"@type":"ListItem","position":6,"name":"Repository","item":"https://gitlab.sci.uwo.ca/courses/2025/09/COMPSCI2212/group16/-/blob/main/src/Npc.java?ref_type=heads"}]}


</script>
<div data-testid="breadcrumb-links" id="js-vue-page-breadcrumbs-wrapper">
<div data-breadcrumbs-json="[{&quot;text&quot;:&quot;courses&quot;,&quot;href&quot;:&quot;/courses&quot;,&quot;avatarPath&quot;:null},{&quot;text&quot;:&quot;2025&quot;,&quot;href&quot;:&quot;/courses/2025&quot;,&quot;avatarPath&quot;:null},{&quot;text&quot;:&quot;09&quot;,&quot;href&quot;:&quot;/courses/2025/09&quot;,&quot;avatarPath&quot;:null},{&quot;text&quot;:&quot;COMPSCI2212&quot;,&quot;href&quot;:&quot;/courses/2025/09/COMPSCI2212&quot;,&quot;avatarPath&quot;:null},{&quot;text&quot;:&quot;group16&quot;,&quot;href&quot;:&quot;/courses/2025/09/COMPSCI2212/group16&quot;,&quot;avatarPath&quot;:null},{&quot;text&quot;:&quot;Repository&quot;,&quot;href&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/blob/main/src/Npc.java?ref_type=heads&quot;,&quot;avatarPath&quot;:null}]" id="js-vue-page-breadcrumbs"></div>
<div id="js-injected-page-breadcrumbs"></div>
<div id="js-page-breadcrumbs-extra"></div>
</div>


</div>

</div>
</div>

<div class="container-fluid container-limited project-highlight-puc">
<main class="content" id="content-body" itemscope itemtype="http://schema.org/SoftwareSourceCode">
<div id="js-drawer-container"></div>
<div class="flash-container flash-container-page sticky" data-testid="flash-container">
<div id="js-global-alerts"></div>
</div>






<div class="js-signature-container" data-signatures-path="/courses/2025/09/COMPSCI2212/group16/-/commits/872c5d1715031dce3f1643e353391010dc132a1b/signatures?limit=1"></div>

<div class="tree-holder gl-pt-5" id="tree-holder">
<div data-blob-path="src/Npc.java" data-breadcrumbs-can-collaborate="true" data-breadcrumbs-can-edit-tree="true" data-breadcrumbs-can-push-code="true" data-breadcrumbs-can-push-to-branch="true" data-breadcrumbs-fork-new-blob-path="/courses/2025/09/COMPSCI2212/group16/-/forks?continue%5Bnotice%5D=You%27re+not+allowed+to+make+changes+to+this+project+directly.+A+fork+of+this+project+has+been+created+that+you+can+make+changes+in%2C+so+you+can+submit+a+merge+request.&amp;continue%5Bnotice_now%5D=You%27re+not+allowed+to+make+changes+to+this+project+directly.+A+fork+of+this+project+is+being+created+that+you+can+make+changes+in%2C+so+you+can+submit+a+merge+request.&amp;continue%5Bto%5D=%2Fcourses%2F2025%2F09%2FCOMPSCI2212%2Fgroup16%2F-%2Fnew%2Fmain%2Fsrc%2FNpc.java&amp;namespace_key=5898" data-breadcrumbs-fork-new-directory-path="/courses/2025/09/COMPSCI2212/group16/-/forks?continue%5Bnotice%5D=You%27re+not+allowed+to+make+changes+to+this+project+directly.+A+fork+of+this+project+has+been+created+that+you+can+make+changes+in%2C+so+you+can+submit+a+merge+request.+Try+to+create+a+new+directory+again.&amp;continue%5Bnotice_now%5D=You%27re+not+allowed+to+make+changes+to+this+project+directly.+A+fork+of+this+project+is+being+created+that+you+can+make+changes+in%2C+so+you+can+submit+a+merge+request.&amp;continue%5Bto%5D=%2Fcourses%2F2025%2F09%2FCOMPSCI2212%2Fgroup16%2F-%2Fblob%2Fmain%2Fsrc%2FNpc.java%3Fref_type%3Dheads&amp;namespace_key=5898" data-breadcrumbs-fork-upload-blob-path="/courses/2025/09/COMPSCI2212/group16/-/forks?continue%5Bnotice%5D=You%27re+not+allowed+to+make+changes+to+this+project+directly.+A+fork+of+this+project+has+been+created+that+you+can+make+changes+in%2C+so+you+can+submit+a+merge+request.+Try+to+upload+a+file+again.&amp;continue%5Bnotice_now%5D=You%27re+not+allowed+to+make+changes+to+this+project+directly.+A+fork+of+this+project+is+being+created+that+you+can+make+changes+in%2C+so+you+can+submit+a+merge+request.&amp;continue%5Bto%5D=%2Fcourses%2F2025%2F09%2FCOMPSCI2212%2Fgroup16%2F-%2Fblob%2Fmain%2Fsrc%2FNpc.java%3Fref_type%3Dheads&amp;namespace_key=5898" data-breadcrumbs-new-blob-path="/courses/2025/09/COMPSCI2212/group16/-/new/main" data-breadcrumbs-new-branch-path="/courses/2025/09/COMPSCI2212/group16/-/branches/new" data-breadcrumbs-new-dir-path="/courses/2025/09/COMPSCI2212/group16/-/create_dir/main" data-breadcrumbs-new-tag-path="/courses/2025/09/COMPSCI2212/group16/-/tags/new" data-breadcrumbs-selected-branch="ssures46-main-patch-13757" data-breadcrumbs-upload-path="/courses/2025/09/COMPSCI2212/group16/-/create/main" data-download-links="[{&quot;text&quot;:&quot;zip&quot;,&quot;path&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/archive/main/group16-main.zip&quot;},{&quot;text&quot;:&quot;tar.gz&quot;,&quot;path&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/archive/main/group16-main.tar.gz&quot;},{&quot;text&quot;:&quot;tar.bz2&quot;,&quot;path&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/archive/main/group16-main.tar.bz2&quot;},{&quot;text&quot;:&quot;tar&quot;,&quot;path&quot;:&quot;/courses/2025/09/COMPSCI2212/group16/-/archive/main/group16-main.tar&quot;}]" data-escaped-ref="main" data-history-link="/courses/2025/09/COMPSCI2212/group16/-/commits/main" data-http-url="https://gitlab.sci.uwo.ca/courses/2025/09/COMPSCI2212/group16.git" data-project-id="4405" data-project-path="courses/2025/09/COMPSCI2212/group16" data-project-root-path="/courses/2025/09/COMPSCI2212/group16" data-project-short-path="group16" data-ref="main" data-ref-type="heads" data-root-ref="main" data-ssh-url="ssh://git@gitlab.sci.uwo.ca:7999/courses/2025/09/COMPSCI2212/group16.git" data-xcode-url="" id="js-repository-blob-header-app"></div>
<div class="info-well">
<div data-history-link="/courses/2025/09/COMPSCI2212/group16/-/commits/main" id="js-last-commit"></div>
<div class="gl-hidden @sm/panel:gl-block">

</div>
</div>
<div class="blob-content-holder js-per-page" data-blame-per-page="1000" id="blob-content-holder">
<div data-blob-path="src/Npc.java" data-can-download-code="true" data-escaped-ref="main" data-full-name="courses / 2025 / 09 / COMPSCI2212 / group16" data-has-revs-file="false" data-original-branch="main" data-project-path="courses/2025/09/COMPSCI2212/group16" data-ref-type="heads" data-resource-id="gid://gitlab/Project/4405" data-target-branch="ssures46-main-patch-13773" data-user-id="gid://gitlab/User/1354" id="js-view-blob-app">
<div class="gl-spinner-container" role="status"><span aria-hidden class="gl-spinner gl-spinner-md gl-spinner-dark !gl-align-text-bottom"></span><span class="gl-sr-only !gl-absolute">Loading</span>
</div>
</div>
</div>

</div>
<script>
//<![CDATA[
  window.gl = window.gl || {};
  window.gl.webIDEPath = '/-/ide/project/courses/2025/09/COMPSCI2212/group16/edit/main/-/src/Npc.java'


//]]>
</script>
<div data-ambiguous="false" data-ref="main" id="js-ambiguous-ref-modal"></div>

</main>
</div>


</div>
</div>


<script>
//<![CDATA[
if ('loading' in HTMLImageElement.prototype) {
  document.querySelectorAll('img.lazy').forEach(img => {
    img.loading = 'lazy';
    let imgUrl = img.dataset.src;
    // Only adding width + height for avatars for now
    if (imgUrl.indexOf('/avatar/') > -1 && imgUrl.indexOf('?') === -1) {
      const targetWidth = img.getAttribute('width') || img.width;
      imgUrl += `?width=${targetWidth}`;
    }
    img.src = imgUrl;
    img.removeAttribute('data-src');
    img.classList.remove('lazy');
    img.classList.add('js-lazy-loaded');
    img.dataset.testid = 'js-lazy-loaded-content';
  });
}

//]]>
</script>
<script>
//<![CDATA[
gl = window.gl || {};
gl.experiments = {};


//]]>
</script>

</body>
</html>

